package br.com.eventoadmin.mb.padrao;

/**
 *
 * @author Ayslan
 */
public class Format {

    /*
     * Ex: formato dd/MM/yyyy (pt_BR)
     */
    private String data;
    /*
     * Ex: Segunda-feira, 24 de Outubro de 2011 (pt_BR)
     */
    private String dataCompleta;
    /*
     * Ex: formato dd/MM/yyyy HH:mm (pt_BR)
     */
    private String dataHora;
    /*
     * Ex: formato HH:mm (pt_BR)
     */
    private String horaMinuto;
    /*
     * Ex: formato dd/MM/yyyy HH:mm:ss (pt_BR)
     */
    private String dataHoraMinutoSegundo;
    /*
     * Ex: formato dd/MM/aaaa HH:mm:ss (pt_BR)
     */
    private String dataHoraTradizida;
     /*
     * Ex: formato dd/MM/aaaa(pt_BR)
     */
    private String dataTraduzida;

    private char separadorDecimal;
    private char separadorMilhares;
    private String simboloMonetario;
    private String cpf;
    private String cnpj;
    /*
     * Ex: 99/99/9999
     */
    private String mascaraData;
    /*
     * Ex: 99/99/9999 99:99
     */
    private String mascaraDataHora;
     /*
     * Ex: 99:99
     */
    private String mascaraHoraMinuto;
    private String codigoPostal;
    private String telefone;
    
    /*
     * Ex: Março/2013 (pt_BR)
     */
    private String mesAno;
    
    private String placa;
    private String renavam;

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getRenavam() {
        return renavam;
    }

    public void setRenavam(String renavam) {
        this.renavam = renavam;
    }
    
    public char getSeparadorDecimal() {
        return separadorDecimal;
    }

    public void setSeparadorDecimal(char separadorDecimal) {
        this.separadorDecimal = separadorDecimal;
    }

    public char getSeparadorMilhares() {
        return separadorMilhares;
    }

    public void setSeparadorMilhares(char separadorMilhares) {
        this.separadorMilhares = separadorMilhares;
    }

    public String getSimboloMonetario() {
        return simboloMonetario;
    }

    public void setSimboloMonetario(String simboloMonetario) {
        this.simboloMonetario = simboloMonetario;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getDataCompleta() {
        return dataCompleta;
    }

    public void setDataCompleta(String dataCompleta) {
        this.dataCompleta = dataCompleta;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getDataHora() {
        return dataHora;
    }

    public void setDataHora(String dataHora) {
        this.dataHora = dataHora;
    }

    public String getMascaraData() {
        return mascaraData;
    }

    public void setMascaraData(String mascaraData) {
        this.mascaraData = mascaraData;
    }

    public String getDataHoraMinutoSegundo() {
        return dataHoraMinutoSegundo;
    }

    public void setDataHoraMinutoSegundo(String dataHoraMinutoSegundo) {
        this.dataHoraMinutoSegundo = dataHoraMinutoSegundo;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getMascaraDataHora() {
        return mascaraDataHora;
    }

    public void setMascaraDataHora(String mascaraDataHora) {
        this.mascaraDataHora = mascaraDataHora;
    }

    public String getHoraMinuto() {
        return horaMinuto;
    }

    public void setHoraMinuto(String horaMinuto) {
        this.horaMinuto = horaMinuto;
    }

    public String getMascaraHoraMinuto() {
        return mascaraHoraMinuto;
    }

    public void setMascaraHoraMinuto(String mascaraHoraMinuto) {
        this.mascaraHoraMinuto = mascaraHoraMinuto;
    }

    public String getDataHoraTradizida() {
        return dataHoraTradizida;
    }

    public void setDataHoraTradizida(String dataHoraTradizida) {
        this.dataHoraTradizida = dataHoraTradizida;
    }

    public String getDataTraduzida() {
        return dataTraduzida;
    }

    public void setDataTraduzida(String dataTraduzida) {
        this.dataTraduzida = dataTraduzida;
    }

    public String getMesAno() {
        return mesAno;
    }

    public void setMesAno(String mesAno) {
        this.mesAno = mesAno;
    }
    
}
