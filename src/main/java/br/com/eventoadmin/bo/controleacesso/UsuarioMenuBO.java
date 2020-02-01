package br.com.eventoadmin.bo.controleacesso;

import br.com.eventoadmin.dao.controleacesso.PermissaoDAO;
import br.com.eventoadmin.modelo.controleacesso.Permissao;
import br.com.eventoadmin.modelo.controleacesso.Usuario;
import com.xpert.i18n.I18N;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuElement;
import org.primefaces.model.menu.MenuItem;
import org.primefaces.model.menu.MenuModel;
import org.primefaces.model.menu.Submenu;

/**
 *
 * Classe para montar o menu dinamico a partir das permissoes
 *
 * @author ayslan
 */
@Stateless
public class UsuarioMenuBO {

    @EJB
    private PermissaoDAO permissaoDAO;
    @EJB
    private PermissaoBO permissaoBO;

    /**
     * cria o menu do usuario
     *
     * @param usuario
     * @return
     */
    public MenuModel criarMenu(Usuario usuario) {
        List<Permissao> permissoes = permissaoBO.getPermissoes(usuario, true);
        return criarMenu(permissoes);
    }

    /**
     * cria o menu do usuario
     *
     * @param usuario
     * @param root a chave da permissao
     * @return
     */
    public MenuModel criarMenu(Usuario usuario, String root) {
        List<Permissao> permissoes = permissaoBO.getPermissoes(usuario, true);
        return criarMenu(permissoes, root);
    }

    /**
     * cria o menu a partir de uma lista de permissoes
     *
     * @param permissoes
     * @return
     */
    public MenuModel criarMenu(List<Permissao> permissoes) {

        DefaultMenuModel menuModel = new DefaultMenuModel();
        //home
        menuModel.addElement(getMenuHome());

        //elementos dinamicos
        List<MenuElement> elements = getMenuElements(permissoes);
        if (elements != null) {
            for (MenuElement element : elements) {
                menuModel.addElement(element);
            }
        }

        //sair
        menuModel.addElement(getMenuSair());

        return menuModel;
    }

    /**
     * cria o menu do usuario, pode-se passar o root que seria a partir de onde
     * sera pego a permissao
     *
     * @param permissoes
     * @param root
     * @return
     */
    public MenuModel criarMenu(List<Permissao> permissoes, String root) {
        //pegar o menu model completo
        MenuModel menuModelCompleto = criarMenu(permissoes);
        return criarMenu(menuModelCompleto, root);
    }

    /**
     * cria o menu do usuario, pode-se passar o root que seria a partir de onde
     * sera pego a permissao
     *
     * @param menuModelCompleto Menu completo do usuario, a partir dele sera
     * pego os filhos
     * @param root a chave da permissao
     * @return
     */
    public MenuModel criarMenu(MenuModel menuModelCompleto, String root) {
        MenuModel menuModel = new DefaultMenuModel();

        //pegar a permissao pela chave
        Permissao permissao = permissaoDAO.unique("key", root);
        if (permissao != null) {
            //aqui eh passado o id como string pois o "setId" do menu do primefaces tbm eh string
            MenuElement element = encontrarMenu(menuModelCompleto.getElements(), permissao.getId().toString());
            if (element != null) {
                //se for item apenas adicionar o item, senao adicionar os filhos do submenu
                if (element instanceof MenuItem) {
                    menuModel.addElement(element);
                } else if (element instanceof Submenu) {
                    Submenu submenu = (Submenu) element;
                    for (Object menuElement : submenu.getElements()) {
                        if (menuElement instanceof MenuElement) {
                            menuModel.addElement((MenuElement) menuElement);
                        }
                    }
                }
            }
        }
        return menuModel;
    }

    /**
     * metodo que pesquisa dentro dos elmentos o menu que possui o id passado
     * por parametro
     *
     * @param elements
     * @param id
     * @return
     */
    public MenuElement encontrarMenu(List<MenuElement> elements, String id) {

        if (elements != null) {
            for (MenuElement el : elements) {
                if (el.getId() != null && el.getId().equals(id)) {
                    return el;
                }
                if (el instanceof Submenu) {
                    Submenu submenu = (Submenu) el;
                    if (submenu.getElements() != null) {
                        MenuElement element = encontrarMenu(submenu.getElements(), id);
                        if (element != null) {
                            return element;
                        }
                    }
                }
            }
        }
        return null;
    }

    public List<MenuElement> getMenuElements(List<Permissao> permissoes) {

        List<MenuElement> elements = new ArrayList<MenuElement>();

        //urls dinamicas
        if (permissoes != null && !permissoes.isEmpty()) {

            //map para evitar a duplicidade e ter acesso mais rapido as menus
            Map<Permissao, DefaultSubMenu> subMenuMap = new HashMap<Permissao, DefaultSubMenu>();
            Map<Permissao, DefaultMenuItem> itemMenuMap = new HashMap<Permissao, DefaultMenuItem>();

            //map para vincular o item do menu a permissao
            Map<MenuElement, Permissao> permissaoMap = new HashMap<MenuElement, Permissao>();

            //primeiro "for" para adicionar os submenus
            for (Permissao permissao : permissoes) {
                putSubmenu(permissao, subMenuMap, elements, permissaoMap);
            }

            //montar itens
            for (Permissao permissao : permissoes) {
                if (permissao.isPossuiMenu() && permissao.isAtivo()) {
                    putItemMenu(permissao, subMenuMap, itemMenuMap, elements, permissaoMap);
                }
            }
            //ordenar elementos
            order(elements, permissaoMap);
        }

        //ordernar
        return elements;
    }

    public void order(MenuModel menuModel, Map<MenuElement, Permissao> permissaoMap) {
        if (menuModel.getElements() != null) {
            order(menuModel.getElements(), permissaoMap);
        }
    }

    /**
     * Metodo para ordenar os itens do menu baseado na posicao da permissao
     *
     * @param itens
     * @param permissaoMap
     */
    public void order(List<MenuElement> itens, final Map<MenuElement, Permissao> permissaoMap) {
        if (itens != null) {
            Comparator<MenuElement> comparator = new Comparator<MenuElement>() {
                @Override
                public int compare(MenuElement o1, MenuElement o2) {
                    Integer v1 = getOrder(o1, permissaoMap);
                    Integer v2 = getOrder(o2, permissaoMap);
                    return v1.compareTo(v2);
                }
            };
            Collections.sort(itens, comparator);
            for (MenuElement element : itens) {
                if (element instanceof Submenu) {
                    order(((Submenu) element).getElements(), permissaoMap);
                }
            }
        }
    }

    public Integer getOrder(MenuElement element, Map<MenuElement, Permissao> permissaoMap) {
        Permissao permissao = permissaoMap.get(element);
        if (permissao == null || permissao.getOrdenacao() == null) {
            return 0;
        }
        return permissao.getOrdenacao();
    }

    public DefaultMenuItem getMenuHome() {
        DefaultMenuItem item = new DefaultMenuItem();
        item.setValue(I18N.get("menu.home"));
        item.setIcon("ui-icon-home");
        item.setUrl("/view/home.jsf");
        return item;
    }

    public DefaultMenuItem getMenuSair() {
        DefaultMenuItem item = new DefaultMenuItem();
        item.setValue(I18N.get("menu.sair"));
        item.setIcon("ui-icon-close");
        item.setCommand("#{loginMB.logout}");
        return item;
    }

    public void putItemMenu(Permissao permissao, Map<Permissao, DefaultSubMenu> subMenuMap,
            Map<Permissao, DefaultMenuItem> itemMenuMap, List<MenuElement> elements, Map<MenuElement, Permissao> permissaoMap) {
        //se ja estiver adicionado nao adiscionar novamente
        if (itemMenuMap.containsKey(permissao)) {
            return;
        }
        String url = permissao.getUrlMenuVerificado();
        if (url != null && !url.trim().isEmpty()) {
            Submenu submenu = null;
            Permissao permissaoPai = permissaoDAO.getInitialized(permissao.getPermissaoPai());
            if (permissaoPai != null) {
                submenu = subMenuMap.get(permissaoPai);
                //adicionar o menu pai quando não encontrado
                if (submenu == null) {
                    putSubmenu(permissaoPai, subMenuMap, elements, permissaoMap);
                    submenu = subMenuMap.get(permissaoPai);
                }
            }
            //se o menu pai for nulo ou se encontrou o menu pai e ele possui menu pai
            if (permissaoPai == null || submenu != null) {
                DefaultMenuItem item = new DefaultMenuItem();
                item.setId(permissao.getId().toString());
                item.setValue(permissao.getNomeMenuVerificado());
                item.setUrl(permissao.getUrlMenuVerificado());
                itemMenuMap.put(permissao, item);
                permissaoMap.put(item, permissao);
                //adicionar ao submenu quando encontrado, senao adicionar ao root
                if (submenu != null) {
                    submenu.getElements().add(item);
                } else {
                    elements.add(item);
                }
            }
        }
    }

    public void putSubmenu(Permissao permissao, Map<Permissao, DefaultSubMenu> subMenuMap, List<MenuElement> elements, Map<MenuElement, Permissao> permissaoMap) {
        if (permissao != null) {
            if (permissao.isPossuiMenu() && permissao.isAtivo()) {
                String url = permissao.getUrlMenuVerificado();
                if (url == null || url.trim().isEmpty()) {
                    DefaultSubMenu submenu = subMenuMap.get(permissao);
                    //caso a permissao tenha pai deve ser adicionado um submenu desse pai quando nao encontrado
                    if (submenu == null) {
                        submenu = new DefaultSubMenu();
                        submenu.setId(permissao.getId().toString());
                        submenu.setLabel(permissao.getNomeMenuVerificado());
                        subMenuMap.put(permissao, submenu);
                        permissaoMap.put(submenu, permissao);
                        Submenu pai = null;
                        Permissao permissaoPai = permissaoDAO.getInitialized(permissao.getPermissaoPai());
                        if (permissaoPai != null && permissaoPai.isAtivo()) {
                            putSubmenu(permissaoPai, subMenuMap, elements, permissaoMap);
                            pai = subMenuMap.get(permissaoPai);
                        }
                        //setar submenupai
                        if (pai != null) {
                            pai.getElements().add(submenu);
                        } else {
                            //adicionar ao root apenas quando nao possuir pai
                            if (permissaoPai == null) {
                                elements.add(submenu);
                            }
                        }
                    }
                }
            }
        }
    }

}
