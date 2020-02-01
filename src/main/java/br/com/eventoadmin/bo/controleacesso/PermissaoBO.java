package br.com.eventoadmin.bo.controleacesso;

import br.com.eventoadmin.dao.controleacesso.PermissaoDAO;
import br.com.eventoadmin.modelo.controleacesso.Perfil;
import br.com.eventoadmin.modelo.controleacesso.Permissao;
import br.com.eventoadmin.modelo.controleacesso.Usuario;
import com.xpert.core.conversion.Conversion;
import com.xpert.core.crud.AbstractBusinessObject;
import com.xpert.persistence.dao.BaseDAO;
import com.xpert.core.validation.UniqueField;
import com.xpert.core.exception.BusinessException;
import com.xpert.core.validation.UniqueFields;
import com.xpert.utils.CollectionsUtils;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

/**
 *
 * @author Ayslan
 */
@Stateless
public class PermissaoBO extends AbstractBusinessObject<Permissao> {

    @EJB
    private PermissaoDAO permissaoDAO;

    @Override
    public BaseDAO getDAO() {
        return permissaoDAO;
    }

    /**
     * salva a ordenacao das permissoes baseado na posicao na arvore
     *
     * @param permissoes
     */
    public void salvarOrdenacao(TreeNode permissoes) {
        atualizarOrdenacao(permissoes.getChildren());
    }

    /**
     * metodo que seta a permissao pai e a ordenacao no menu, baseado na arvore
     * que foi montada na view
     *
     * @param nodes
     */
    public void atualizarOrdenacao(List<TreeNode> nodes) {
        if (nodes != null) {
            int count = 0;
            for (TreeNode node : nodes) {
                Permissao permissao = (Permissao) node.getData();
                //se o no possuir pai, entao salvar a permissao pai, senao setar nulo
                if (node.getParent() != null) {
                    permissao.setPermissaoPai((Permissao) node.getParent().getData());
                } else {
                    permissao.setPermissaoPai(null);
                }
                permissao.setOrdenacao(count);
                atualizarOrdenacao(node.getChildren());
                count++;
                //atualizar no banco
                permissaoDAO.merge(permissao);
            }
        }
    }

    /**
     * Retorna uma arvore de permissoes de um determinado perfil. Baseada nos
     * menus de atalho de cada perfil
     *
     * @param perfil
     * @return
     */
    public TreeNode getTreeNodeMenu(Perfil perfil) {
        //pegar todas as permissoes
        List<Permissao> permissoes = getPermissoesRaiz();
        //permissoes marcadas no menu
        List<Permissao> permissoesMenu = null;
        if (perfil != null && perfil.getId() != null) {
            permissoesMenu = permissaoDAO.getPermissoesMenu(perfil);
        }
        //permissoes do perfil
        List<Permissao> permissoesPerfil = null;
        if (perfil != null && perfil.getId() != null) {
            permissoesPerfil = permissaoDAO.getPermissoes(perfil);
        }
        return criarTreeNode(null, permissoes, permissoesMenu, permissoesPerfil);
    }

    /**
     * Retorna uma arvore de permissoes de um determinado perfil. A arvore
     * possui todas as permissoes, porem so estarao selecionados as permissoes
     * que o perfil possui. O perfil pode ser passado como nulo tambem
     *
     * @param perfil
     * @return
     */
    public TreeNode getTreeNode(Perfil perfil) {

        //pegar todas as permissoes
        List<Permissao> permissoes = getPermissoesRaiz();

        //pegar as permissoes apenas do perfil
        List<Permissao> permissoesPerfil = null;
        if (perfil != null && perfil.getId() != null) {
            permissoesPerfil = permissaoDAO.getPermissoes(perfil);
        }
        //o null eh o root, que nao precisa ser passado
        return criarTreeNode(null, permissoes, permissoesPerfil, null);
    }

    /**
     * Retorna as permissoes que ficam na raiz, ou seja, as permissoes "sem pai"
     *
     * @return
     */
    public List<Permissao> getPermissoesRaiz() {
        return permissaoDAO.getQueryBuilder()
                .from(Permissao.class)
                .isNull("permissaoPai")
                .orderBy("descricao").getResultList();
    }

    /**
     * Metodo que carrega a arvore para uma lista de permissoes
     *
     * @param root
     * @param permissoes - as permissoes a serem exibidas
     * @param permissoesParaSelecionar - as permissoes que ficarao selecionadas
     * @param permissoesSelecionaveis - as permissoes que se pode selecionar,
     * quando passado null, qualquer um sera selecionavel
     * @return
     */
    public TreeNode criarTreeNode(TreeNode root, List<Permissao> permissoes, List<Permissao> permissoesParaSelecionar, List<Permissao> permissoesSelecionaveis) {

        boolean nullRoot = false;
        if (root == null) {
            root = new DefaultTreeNode();
            root.setExpanded(true);
            nullRoot = true;
        }

        if (permissoes != null) {
            //ordernar permissoes
            ordernar(permissoes);
            for (Permissao permissao : permissoes) {
                TreeNode node = new DefaultTreeNode(permissao, root);
                //expandir apenas os primeiros filhos
                node.setExpanded(nullRoot);
                //se as selecionaveis forem nulas ou 
                if (permissoesSelecionaveis == null || permissoesSelecionaveis.contains(permissao)) {
                    node.setSelectable(true);
                } else {
                    node.setSelectable(false);
                }
                if (permissoesParaSelecionar != null && permissoesParaSelecionar.contains(permissao)) {
                    node.setSelected(true);
                }
                List<Permissao> filhas = permissaoDAO.getInitialized(permissao.getPermissoesFilhas());
                if (filhas != null) {
                    //chamada recursiva para adicionar os filhos
                    criarTreeNode(node, filhas, permissoesParaSelecionar, permissoesSelecionaveis);
                    root.getChildren().add(node);
                }
            }
        }

        return root;
    }

    /**
     * Ordena a lista de permissoes baseada no campo ordenacao (do menor para o
     * maior)
     *
     * @param permissoes
     */
    public static void ordernar(List<Permissao> permissoes) {
        if (permissoes != null) {
            Comparator<Permissao> comparator = new Comparator<Permissao>() {
                @Override
                public int compare(Permissao o1, Permissao o2) {
                    Integer v1 = o1.getOrdenacao() != null ? o1.getOrdenacao() : 0;
                    Integer v2 = o2.getOrdenacao() != null ? o2.getOrdenacao() : 0;
                    return v1.compareTo(v2);
                }
            };
            Collections.sort(permissoes, comparator);
        }
    }

    /**
     *
     * Retorna uma lista de permissoes a partir de um usuario. Caso seja um
     * superusuario retornar todas as permissoes. Casa seja nao seja
     * superusuario, retornar as permissoes dos perfis dele.
     *
     * @param usuario
     * @param apenasAtivas Indica se devem ser retornadas apenas as ativas
     * @return
     */
    public List getPermissoes(Usuario usuario, boolean apenasAtivas) {
        List<Permissao> permissoes = new ArrayList<Permissao>();
        if (usuario != null) {
            if (usuario.isSuperUsuario()) {
                permissoes = permissaoDAO.getPermissoes(apenasAtivas);
            } else {
                permissoes = permissaoDAO.getPermissoes(usuario, apenasAtivas);
            }
        }
        permissoes = getChildren(permissoes, apenasAtivas);
        return permissoes;
    }

    /**
     *
     * Retorna uma lista de permissoes a partir de um usuario. Caso seja um
     * superusuario retornar todas as permissoes. Casa seja nao seja
     * superusuario, retornar as permissoes dos perfis dele.
     *
     * Este metodo retorna tanto as ativas com as inativas.
     *
     * @param usuario
     * @return
     */
    public List getPermissoes(Usuario usuario) {
        return getPermissoes(usuario, false);
    }

    /**
     * pega todas as permissoes filhas em cascada a partir da lista passada
     *
     * @param permissoes
     * @param apenasAtivas Indica se sao apenas as permissoes ativas
     * @return
     */
    private List<Permissao> getChildren(List<Permissao> permissoes, boolean apenasAtivas) {
        List<Permissao> permissoesAdd = new ArrayList<Permissao>();
        if (permissoes != null) {
            for (Permissao permissao : permissoes) {
                if (!permissoesAdd.contains(permissao)) {
                    //se nao indicado se sao apenas as ativas ou se a permissao for ativa, adicinar
                    if (apenasAtivas == false || permissao.isAtivo()) {
                        permissoesAdd.add(permissao);
                    }
                }
                permissao.getPermissoesFilhas().size();
                List<Permissao> children = getChildren(permissao.getPermissoesFilhas(), apenasAtivas);
                if (children != null && !children.isEmpty()) {
                    for (Permissao child : children) {
                        if (!permissoesAdd.contains(child)) {
                            //se nao indicado se sao apenas as ativas ou se a permissao for ativa, adicinar
                            if (apenasAtivas == false || permissao.isAtivo()) {
                                permissoesAdd.add(child);
                            }
                        }
                    }
                }
            }
        }
        return permissoesAdd;
    }

    /**
     * Muda o status de uma permissao
     *
     * @param permissao
     * @param ativo Indica se o status desejado eh o ativo ou nao
     * @param emCascata Indica se as permissoes filhas sera afetadas tambem (e
     * as filhas das filhas)
     */
    public void alterarStatus(Permissao permissao, boolean ativo, boolean emCascata) {
        permissao.setAtivo(ativo);
        permissaoDAO.merge(permissao);
        if (emCascata == true) {
            List<Permissao> filhas = permissaoDAO.getInitialized(permissao.getPermissoesFilhas());
            if (filhas != null) {
                for (Permissao filha : filhas) {
                    alterarStatus(filha, ativo, emCascata);
                }
            }
        }
    }

    /**
     * Inativa uma permissao
     *
     * @param permissao
     * @param emCascata Indica se as permissoes filhas sera inativadas tambem (e
     * as filhas das filhas)
     */
    public void inativar(Permissao permissao, boolean emCascata) {
        //passar ativo = false
        alterarStatus(permissao, false, emCascata);
    }

    /**
     * Ativa uma permissao
     *
     * @param permissao
     * @param emCascata Indica se as permissoes filhas sera ativadas tambem (e
     * as filhas das filhas)
     */
    public void ativar(Permissao permissao, boolean emCascata) {
        //passar ativo = true
        alterarStatus(permissao, true, emCascata);
    }

    @Override
    public void save(Permissao permissao) throws BusinessException {
        super.save(permissao);
    }

    @Override
    public List<UniqueField> getUniqueFields() {
        return new UniqueFields().add("key");
    }

    @Override
    public void validate(Permissao permissao) throws BusinessException {

        //permissao nao pode ser pai dela mesma
        if (permissao.getId() != null && permissao.getPermissaoPai() != null) {
            if (permissao.getId().equals(permissao.getPermissaoPai().getId())) {
                throw new BusinessException("business.permissaoNaoPodePaiDelaMesma");
            }
        }
    }

    @Override
    public boolean isAudit() {
        return true;
    }

    /**
     * Seta o campo CaminhoPermissao da Permissao. O formato eh permissao 1 >
     * permissao 2 > permissao 3
     *
     * @param listaPermissoes
     * @param adicionarPropriaPermissao Indica se o caminho deve ir ateh o final
     * indicando a propria permissao
     */
    public void criarCaminhoPermissao(List<Permissao> listaPermissoes, boolean adicionarPropriaPermissao) {
        if (listaPermissoes != null) {
            for (Permissao permissao : listaPermissoes) {
                StringBuilder builder = new StringBuilder();

                List<Permissao> permissoes = new ArrayList<Permissao>();
                Permissao permissaoAtual = permissao;
                while (permissaoAtual != null) {
                    permissoes.add(permissaoAtual);
                    permissaoAtual = permissaoDAO.getInitialized(permissaoAtual.getPermissaoPai());
                }

                Collections.reverse(permissoes);
                for (int i = 0; i < permissoes.size(); i++) {
                    if (adicionarPropriaPermissao == true || !permissoes.get(i).equals(permissao)) {
                        if (i > 0) {
                            builder.append(" > ");
                        }
                        if (permissoes.get(i).equals(permissao)) {
                            builder.append("<b style='font-size: 13px;'>").append(permissoes.get(i).getNomeMenuVerificado()).append("</b>");
                        } else {
                            builder.append(permissoes.get(i).getNomeMenuVerificado());
                        }
                    }
                }
                permissao.setCaminhoPermissao(builder.toString());
            }
        }
    }

    /**
     * retorna uma lista de permissoes baseada na
     *
     * @param query
     * @param listaPermissoes
     * @return
     */
    public List<Permissao> pesquisarPermissao(String query, List<Permissao> listaPermissoes) {
        List<Permissao> permissoes = new ArrayList<Permissao>();

        if (listaPermissoes != null) {
            for (Permissao permissao : listaPermissoes) {
                if (permissao.isPossuiMenu() && permissao.getUrl() != null && !permissao.getUrl().isEmpty()) {
                    if (Conversion.removeAccent(permissao.getCaminhoPermissao()).toLowerCase().contains(Conversion.removeAccent(query.toLowerCase()))
                            || (permissao.getNomeMenu() != null && permissao.getNomeMenu().toLowerCase().contains(query.toLowerCase()))) {
                        permissoes.add(permissao);
                    }
                }
            }
        }

        CollectionsUtils.orderAsc(permissoes, "caminhoPermissao");

        return permissoes;
    }
}
