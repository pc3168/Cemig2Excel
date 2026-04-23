package org.example.util;

import org.example.model.Fatura;
import org.example.model.FaturaBruta;

public class FaturaParser {

    private FaturaBruta faturaBruta;
    private Fatura fatura;

    public FaturaParser(FaturaBruta faturaBruta) {
        this.faturaBruta = faturaBruta;
        this.fatura = new Fatura();
        System.out.println(faturaBruta.cabecalho());
    }

    private Fatura converterCabecalho() {

        return new Fatura();
    }

    private Fatura converterValoresFaturados() {
        return new Fatura();
    }

    private Fatura converterHistoricoConsumo() {
        return new Fatura();
    }

    private Fatura converterReservadoFisco() {
        return new Fatura();
    }

    private Fatura converterDadosPessoais() {
        return new Fatura();
    }

    private Fatura converterUnidadeConsumidora() {
        return new Fatura();
    }

    private Fatura converterReferenteVencimento() {
        return new Fatura();
    }

    private Fatura converterInformacoesGerais() {
        return new Fatura();
    }

    private Fatura converterInformacoesTecnicas() {
        return new Fatura();
    }

    public Fatura getFatura(){
        return this.fatura;
    }

}
