package org.example.util;

import org.example.model.Fatura;
import org.example.model.FaturaBruta;

import java.time.YearMonth;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FaturaParser {

    private FaturaBruta faturaBruta;
    private Fatura fatura;

    public FaturaParser(FaturaBruta faturaBruta) {
        this.faturaBruta = faturaBruta;
        this.fatura = new Fatura();
        converterReferenteVencimento();
        converterUnidadeConsumidora();
        converterInformacoesTecnicas();
        converterReservadoFisco();
    }

    private Fatura converterCabecalho() {
        return fatura;
    }

    private Fatura converterValoresFaturados() {
        return fatura;
    }

    private Fatura converterHistoricoConsumo() {
        return fatura;
    }

    private Fatura converterReservadoFisco() {
        String valor = faturaBruta.reservadoFisco();
        String[] icms = extrairLinhaImposto(valor, "ICMS");
        String[] pasep = extrairLinhaImposto(valor, "PASEP");
        String[] cofins = extrairLinhaImposto(valor, "COFINS");
        fatura.setIcmsBase(icms[0]);
        fatura.setIcmsAliquota(icms[1]);
        fatura.setIcmsValor(icms[2]);

        fatura.setPasepBase(pasep[0]);
        fatura.setPasepAliquota(pasep[1]);
        fatura.setPasepValor(pasep[2]);

        fatura.setCofinsBase(cofins[0]);
        fatura.setCofinsAliquota(cofins[1]);
        fatura.setCofinsValor(cofins[2]);
        return fatura;
    }

    private Fatura converterDadosPessoais() {
        return fatura;
    }

    private Fatura converterUnidadeConsumidora() {
        String valor = faturaBruta.unidadeConsumidora();
        String[] split = valor.split(System.lineSeparator());
        fatura.setUnidadeConsumidora(split[1]);
        return fatura;
    }

    private Fatura converterReferenteVencimento() {
        String valor = faturaBruta.referenteVencimento();
        String[] split = valor.split(System.lineSeparator());
        fatura.setAnoMesReferencia(converterManual(split[1].substring(0,8)));
        fatura.setVencimento(extrairData(split[1]));
        fatura.setValorPagar(extrairValor(split[1]));
        fatura.setNumeroNF(extrairDadoPorLabel(split[2], "N"));
        fatura.setSerie(extrairDadoPorLabel(split[2], "SÉRIE"));
        fatura.setDataEmissao(extrairData(split[3]));
        fatura.setChaveAcesso(split[7]);
        fatura.setProtocoloAutorizacao(extrairDadoPorLabel(split[8],"Protocolo de autorização"));
        return fatura;
    }

    private Fatura converterInformacoesGerais() {
        return fatura;
    }

    private Fatura converterInformacoesTecnicas() {
        String valor = faturaBruta.informacoesTecnicas();
        String[] split = valor.split(System.lineSeparator());
        String[] ultimaLinha = split[3].split(" ");
        fatura.setTipoMedicao(ultimaLinha[0] + " " + ultimaLinha[1]);
        fatura.setMedicao(ultimaLinha[2]);
        fatura.setLeituraAnterior(ultimaLinha[3]);
        fatura.setLeituraAtual(ultimaLinha[4]);
        fatura.setConsumoKWh(ultimaLinha[6]);
        return fatura;
    }

    public Fatura getFatura(){
        return this.fatura;
    }



    private String converterManual(String data) {
        try {
            String[] partes = data.split("/");
            int ano = Integer.parseInt(partes[1]);
            int mes = switch (partes[0].toUpperCase()) {
                case "JAN" -> 1; case "FEV" -> 2; case "MAR" -> 3;
                case "ABR" -> 4; case "MAI" -> 5; case "JUN" -> 6;
                case "JUL" -> 7; case "AGO" -> 8; case "SET" -> 9;
                case "OUT" -> 10; case "NOV" -> 11; case "DEZ" -> 12;
                default -> 0;
            };
            return String.valueOf(YearMonth.of(ano, mes));
        } catch (Exception ex) {
            return data;
        }
    }

    private String extrairValor(String texto) {
        if (texto == null) return "";

        // Regex para valores como: 165,37 ou 1.250,50
        // Procura por números seguidos de vírgula e dois dígitos
        Pattern pattern = Pattern.compile("(\\d{1,3}(\\.\\d{3})*,\\d{2})");
        Matcher matcher = pattern.matcher(texto);

        if (matcher.find()) {
            return matcher.group(1); // Retorna apenas o número formatado (ex: 165,37)
        }

        return null;
    }

    private String extrairData(String texto) {
        if (texto == null) return null;

        // Regex para encontrar o padrão 00/00/0000
        Pattern pattern = Pattern.compile("(\\d{2}/\\d{2}/\\d{4})");
        Matcher matcher = pattern.matcher(texto);

        if (matcher.find()) {
            return matcher.group(1); // Retorna a primeira data que encontrar
        }

        return null;
    }

    private String extrairNumeroNota(String texto) {
        if (texto == null) return "";

        // Procura por "Nº" seguido de espaços (opcional) e captura os dígitos
        Pattern pattern = Pattern.compile("Nº\\s*(\\d+)");
        Matcher matcher = pattern.matcher(texto);

        if (matcher.find()) {
            return matcher.group(1); // Retorna "251723252"
        }
        return "";
    }

    private String extrairSerieNota(String texto) {
        if (texto == null) return "";

        // Procura por "SÉRIE" seguido de espaços e captura os dígitos
        Pattern pattern = Pattern.compile("SÉRIE\\s*(\\d+)");
        Matcher matcher = pattern.matcher(texto);

        if (matcher.find()) {
            return matcher.group(1); // Retorna "000"
        }
        return "";
    }

    private  String extrairDadoPorLabel(String texto, String label) {
        if (texto == null || label == null) return "";

        // Regex explicada:
        // (?:...) -> Grupo de não captura para o rótulo
        // \\s*[:º]?\\s* -> Procura opcionalmente por espaço, dois pontos ou o símbolo de número
        // (\\d+) -> Captura uma sequência de dígitos (nosso alvo)
        String regex = label + "\\s*[:º]?\\s*(\\d+)";

        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(texto);

        if (matcher.find()) {
            return matcher.group(1);
        }
        return "";
    }

    private static String[] extrairLinhaImposto(String texto, String imposto) {
        // Regex: Procura o nome do imposto e captura as 3 sequências de números/vírgulas à frente
        String regex = imposto + "\\s+([\\d,.]+)\\s+([\\d,.]+)\\s+([\\d,.]+)";

        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(texto);

        if (matcher.find()) {
            return new String[] {
                    matcher.group(1), // Base de cálculo
                    matcher.group(2), // Alíquota
                    matcher.group(3)  // Valor do Imposto
            };
        }
        return new String[] {"0,00", "0,00", "0,00"};
    }

}
