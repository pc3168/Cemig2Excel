package org.example;

import org.example.form.TelaProcessador;
import org.example.util.FaturaProcessor;

import javax.swing.*;
import java.io.File;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        File file = new File("cemig/22042026210255.pdf");
        //File file = new File("cemig/22042026210315.pdf");
        //File file = new File("cemig/22042026210325.pdf");

        SwingUtilities.invokeLater(() -> {
            new TelaProcessador().setVisible(true);
        });

//        FaturaProcessor faturaProcessor = new FaturaProcessor();
//        faturaProcessor.processarDiretorio("cemig", "saida/");

//
//        System.out.println("==================================");
//        System.out.println(lerPdfToList.valoresFaturados());
//        System.out.println("==================================");
//        System.out.println(lerPdfToList.informacoesGerais());
//        System.out.println("==================================");
//        System.out.println(lerPdfToList.informacoesTecnicas());
//        System.out.println("==================================");
//        System.out.println(lerPdfToList.historicoConsumo());
//        System.out.println("==================================");
//        System.out.println(lerPdfToList.cabecalho());
//        System.out.println("==================================");
//        System.out.println(lerPdfToList.reservadoFisco());
//        System.out.println("==================================");
//        System.out.println(lerPdfToList.dadosPessoais());
//        System.out.println("==================================");
//        System.out.println(lerPdfToList.referenteVencimento());
//        System.out.println("==================================");
//        System.out.println(lerPdfToList.unidadeConsumidora());
//        System.out.println("==================================");
    }

}