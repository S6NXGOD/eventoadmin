package br.com.eventoadmin.mb.padrao;

import com.xpert.i18n.I18N;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Locale;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 * @ManagedBean que retorna formatos padronizados, como mascaras e padroes de data e hora
 * 
 * @author Ayslan
 */
@ManagedBean
@SessionScoped
public class FormatMB implements Serializable{

    private Format format;

    @PostConstruct
    public void init() {
        Locale locale = I18N.getLocale();
        createFormat(locale);
    }

    public Format createFormat(Locale locale) {
        format = new Format();

        DecimalFormatSymbols decimalFormatSymbols = new DecimalFormatSymbols(locale);
        format.setSeparadorDecimal(decimalFormatSymbols.getDecimalSeparator());
        format.setSeparadorMilhares(decimalFormatSymbols.getGroupingSeparator());
        format.setSimboloMonetario(decimalFormatSymbols.getCurrencySymbol());

        String dataCompleta = ((SimpleDateFormat) DateFormat.getDateInstance(SimpleDateFormat.FULL, locale)).toPattern();
        format.setDataCompleta(dataCompleta);

        format.setData("dd/MM/yyyy");
        format.setPlaca("aaa9999");
        format.setRenavam("999999999?99");

        /*
         * TODO verificar mascaras para outros idiomas
         * campos que poderão varia conforme o idioma
         */
        format.setCodigoPostal("99.999-999");
        format.setCnpj("99.999.999/9999-99");
        format.setCpf("999.999.999-99");
        format.setDataHora("dd/MM/yyyy HH:mm");
        format.setDataHoraMinutoSegundo("dd/MM/yyyy HH:mm:ss");
        format.setDataTraduzida("dd/MM/aaaa");
        format.setDataHoraTradizida("dd/MM/aaaa HH:mm");
        format.setHoraMinuto("HH:mm");
        format.setMascaraData("99/99/9999");
        format.setMascaraHoraMinuto("99:99");
        format.setMascaraDataHora("99/99/9999 99:99");
        format.setTelefone("(99)99999999?9");
        format.setMesAno("MMMMM/yyyy");

        return format;
    }

    public Format getFormat() {
        return format;
    }

    public void setFormat(Format format) {
        this.format = format;
    }
}
