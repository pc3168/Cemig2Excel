package org.example.model;

public record FaturaBruta(
        String cabecalho,
        String valoresFaturados,
        String historicoConsumo,
        String reservadoFisco,
        String dadosPessoais,
        String unidadeConsumidora,
        String referenteVencimento,
        String informacoesGerais,
        String informacoesTecnicas
) {
}
