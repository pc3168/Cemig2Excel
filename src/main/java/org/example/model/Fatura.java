package org.example.model;

public class Fatura {

    /*ReferenteVencimento*/
    private String anoMesReferencia;
    private String vencimento;
    private String valorPagar;
    private String numeroNF;
    private String serie;
    private String dataEmissao;
    private String chaveAcesso;
    private String protocoloAutorizacao;
    /*unidade consumidora*/
    private String unidadeConsumidora;

    /*Informações Técnicas*/
    private String tipoMedicao;
    private String medicao;
    private String leituraAnterior;
    private String leituraAtual;
    private String consumoKWh;

    /*Reservado ao Fisco*/
    private String icmsBase;
    private String icmsAliquota;
    private String icmsValor;
    private String pasepBase;
    private String pasepAliquota;
    private String pasepValor;
    private String cofinsBase;
    private String cofinsAliquota;
    private String cofinsValor;

    public String getNumeroNF() {
        return numeroNF;
    }

    public void setNumeroNF(String numeroNF) {
        this.numeroNF = numeroNF;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public String getDataEmissao() {
        return dataEmissao;
    }

    public void setDataEmissao(String dataEmissao) {
        this.dataEmissao = dataEmissao;
    }

    public String getChaveAcesso() {
        return chaveAcesso;
    }

    public void setChaveAcesso(String chaveAcesso) {
        this.chaveAcesso = chaveAcesso;
    }

    public String getProtocoloAutorizacao() {
        return protocoloAutorizacao;
    }

    public void setProtocoloAutorizacao(String protocoloAutorizacao) {
        this.protocoloAutorizacao = protocoloAutorizacao;
    }

    public String getUnidadeConsumidora() {
        return unidadeConsumidora;
    }

    public void setUnidadeConsumidora(String unidadeConsumidora) {
        this.unidadeConsumidora = unidadeConsumidora;
    }

    public String getValorPagar() {
        return valorPagar;
    }

    public void setValorPagar(String valorPagar) {
        this.valorPagar = valorPagar;
    }

    public String getVencimento() {
        return vencimento;
    }

    public void setVencimento(String vencimento) {
        this.vencimento = vencimento;
    }


    public String getTipoMedicao() {
        return tipoMedicao;
    }

    public void setTipoMedicao(String tipoMedicao) {
        this.tipoMedicao = tipoMedicao;
    }

    public String getMedicao() {
        return medicao;
    }

    public void setMedicao(String medicao) {
        this.medicao = medicao;
    }

    public String getLeituraAnterior() {
        return leituraAnterior;
    }

    public void setLeituraAnterior(String leituraAnterior) {
        this.leituraAnterior = leituraAnterior;
    }

    public String getLeituraAtual() {
        return leituraAtual;
    }

    public void setLeituraAtual(String leituraAtual) {
        this.leituraAtual = leituraAtual;
    }

    public String getConsumoKWh() {
        return consumoKWh;
    }

    public void setConsumoKWh(String consumoKWh) {
        this.consumoKWh = consumoKWh;
    }

    public String getIcmsBase() {
        return icmsBase;
    }

    public void setIcmsBase(String icmsBase) {
        this.icmsBase = icmsBase;
    }

    public String getIcmsAliquota() {
        return icmsAliquota;
    }

    public void setIcmsAliquota(String icmsAliquota) {
        this.icmsAliquota = icmsAliquota;
    }

    public String getIcmsValor() {
        return icmsValor;
    }

    public void setIcmsValor(String icmsValor) {
        this.icmsValor = icmsValor;
    }

    public String getPasepBase() {
        return pasepBase;
    }

    public void setPasepBase(String pasepBase) {
        this.pasepBase = pasepBase;
    }

    public String getPasepAliquota() {
        return pasepAliquota;
    }

    public void setPasepAliquota(String pasepAliquota) {
        this.pasepAliquota = pasepAliquota;
    }

    public String getPasepValor() {
        return pasepValor;
    }

    public void setPasepValor(String pasepValor) {
        this.pasepValor = pasepValor;
    }

    public String getCofinsBase() {
        return cofinsBase;
    }

    public void setCofinsBase(String cofinsBase) {
        this.cofinsBase = cofinsBase;
    }

    public String getCofinsAliquota() {
        return cofinsAliquota;
    }

    public void setCofinsAliquota(String cofinsAliquota) {
        this.cofinsAliquota = cofinsAliquota;
    }

    public String getCofinsValor() {
        return cofinsValor;
    }

    public void setCofinsValor(String cofinsValor) {
        this.cofinsValor = cofinsValor;
    }

    public String getAnoMesReferencia() {
        return anoMesReferencia;
    }

    public void setAnoMesReferencia(String anoMesReferencia) {
        this.anoMesReferencia = anoMesReferencia;
    }

    @Override
    public String toString() {
        return String.join(";",
                anoMesReferencia,
                vencimento,
                valorPagar,
                numeroNF,
                serie,
                dataEmissao,
                chaveAcesso,
                protocoloAutorizacao,
                unidadeConsumidora,
                tipoMedicao,
                medicao,
                leituraAnterior,
                leituraAtual,
                consumoKWh,
                icmsBase,
                icmsAliquota,
                icmsValor,
                pasepBase,
                pasepAliquota,
                pasepValor,
                cofinsBase,
                cofinsAliquota,
                cofinsValor
        );
    }

    // Método auxiliar para o cabeçalho (opcional, mas muito útil)
    public static String getCabecalhoCsv() {
        return "Mês Ref;Vencimento;Valor a Pagar;Nº NF;Série;Emissão;Chave;Protocolo;UC;Medição;Valor Medido;Leitura Ant;Leitura Atual;Consumo KWh;ICMS Base;ICMS Alíquota;ICMS Valor;PASEP Base;PASEP Alíquota;PASEP Valor;COFINS Base;COFINS Alíquota;COFINS Valor";
    }
}
