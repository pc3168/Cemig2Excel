package org.example.model;

public class FaturaParser {

    private FaturaBruta faturaBruta;
    private Fatura fatura;

    public FaturaParser(FaturaBruta faturaBruta) {
        this.faturaBruta = faturaBruta;
        this.fatura = new Fatura();
    }

    public Fatura converterCabecalho() {
        return new Fatura();
    }

    public Fatura converterValoresFaturados() {
        return new Fatura();
    }

    public Fatura converterHistoricoConsumo() {
        return new Fatura();
    }

    public Fatura converterReservadoFisco() {
        return new Fatura();
    }

    public Fatura converterDadosPessoais() {
        return new Fatura();
    }

    public Fatura converterUnidadeConsumidora() {
        return new Fatura();
    }

    public Fatura converterReferenteVencimento() {
        return new Fatura();
    }

    public Fatura converterInformacoesGerais() {
        return new Fatura();
    }

    public Fatura converterInformacoesTecnicas() {
        return new Fatura();
    }

    public Fatura getFatura(){
        return this.fatura;
    }

}
