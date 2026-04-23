package org.example.util;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.example.layout.BaseLayout;

import java.io.File;
import java.io.IOException;


public class LerPdfParser implements BaseLayout {

    private final File file;
    private String text;

    private int valoresFaturados ;
    private int historicoConsumo ;
    private int reservadoFisco;
    private int infomacoesGerais;
    private int rodaPeFaleComCemig;
    private int refereteVencimento;
    private int classeSubclasse;
    private int informacoesTecnicas;
    private int documentoAuxilio; // inicio do documento
    private int unidadeConsumidora;
    private int cpf;

    public LerPdfParser(File file) {
        this.file = file;
    }

    public void lerPdf(){
        try (PDDocument document = Loader.loadPDF(file)) {
            PDFTextStripper stripper = new PDFTextStripper();

            stripper.setWordSeparator(" ");

            text = stripper.getText(document);

            //System.out.println(text);

            valoresFaturados = text.indexOf("Valores Faturados");
            historicoConsumo = text.indexOf("Histórico de Consumo");
            reservadoFisco = text.indexOf("Reservado ao Fisco");
            infomacoesGerais = text.indexOf("Informações Gerais");
            rodaPeFaleComCemig = text.indexOf("Fale com CEMIG");
            refereteVencimento = text.indexOf("Referente a Vencimento");
            classeSubclasse = text.indexOf("Classe Subclasse");
            informacoesTecnicas = text.indexOf("Informações Técnicas");
            documentoAuxilio = text.indexOf("DOCUMENTO AUXILIAR");
            unidadeConsumidora = text.indexOf("UNIDADE CONSUMIDORA");
            cpf = text.indexOf("CPF");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public String cabecalho() {
        if (classeSubclasse != -1){
            return text.substring(classeSubclasse, informacoesTecnicas);
        }
        return "";
    }

    @Override
    public String valoresFaturados() {
        if (valoresFaturados != -1){
            return text.substring(valoresFaturados, historicoConsumo);
        }
        return "";
    }

    @Override
    public String historicoConsumo() {
        if (historicoConsumo != -1){
            return text.substring(historicoConsumo, reservadoFisco);
        }
        return "";
    }

    @Override
    public String reservadoFisco() {
        if (reservadoFisco != -1){
            return text.substring(reservadoFisco, rodaPeFaleComCemig);
        }
        return "";
    }

    @Override
    public String dadosPessoais() {
        if (cpf != -1){
            return text.substring(cpf - 130, cpf + 18);
        }
        return "";
    }

    @Override
    public String unidadeConsumidora() {
        if (unidadeConsumidora != -1){
            return text.substring(unidadeConsumidora, refereteVencimento);
        }
        return "";
    }

    @Override
    public String referenteVencimento() {
        if (refereteVencimento != -1){
            return text.substring(refereteVencimento, classeSubclasse);
        }
        return "";
    }

    @Override
    public String informacoesGerais() {
        if (infomacoesGerais != -1){
            return text.substring(infomacoesGerais);
        }
        return "";
    }

    @Override
    public String informacoesTecnicas() {
        if (informacoesTecnicas != -1){
            return text.substring(informacoesTecnicas, documentoAuxilio);
        }
        return "";
    }
}
