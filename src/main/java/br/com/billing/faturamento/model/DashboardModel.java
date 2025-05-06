package br.com.billing.faturamento.model;

public class DashboardModel {

    private String mesAtual;
    private String maiorDespesa;
    private String menorDespesa;
    private String maiorReceita;
    private String menorReceita;

    public DashboardModel() {
    }

    public DashboardModel(String mesAtual, String maiorDespesa, String menorDespesa, String maiorReceita, String menorReceita) {
        this.mesAtual = mesAtual;
        this.maiorDespesa = maiorDespesa;
        this.menorDespesa = menorDespesa;
        this.maiorReceita = maiorReceita;
        this.menorReceita = menorReceita;
    }

    public String getMesAtual() {
        return mesAtual;
    }

    public void setMesAtual(String mesAtual) {
        this.mesAtual = mesAtual;
    }

    public String getMaiorDespesa() {
        return maiorDespesa;
    }

    public void setMaiorDespesa(String maiorDespesa) {
        this.maiorDespesa = maiorDespesa;
    }

    public String getMenorDespesa() {
        return menorDespesa;
    }

    public void setMenorDespesa(String menorDespesa) {
        this.menorDespesa = menorDespesa;
    }

    public String getMaiorReceita() {
        return maiorReceita;
    }

    public void setMaiorReceita(String maiorReceita) {
        this.maiorReceita = maiorReceita;
    }

    public String getMenorReceita() {
        return menorReceita;
    }

    public void setMenorReceita(String menorReceita) {
        this.menorReceita = menorReceita;
    }
}