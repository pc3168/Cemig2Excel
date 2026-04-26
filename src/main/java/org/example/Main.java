package org.example;

import org.example.form.TelaProcessador;
import org.example.model.Parametro;
import org.example.util.FaturaProcessor;

import javax.swing.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

//        FaturaProcessor faturaProcessor = new FaturaProcessor();
//        faturaProcessor.processarDiretorio("cemig", "saida/");

        String version = "1.0";

        // Se não houver argumentos ou o primeiro for "--gui" ou "grafica"
        if (args.length == 0 || args[0].equalsIgnoreCase("--gui")) {

            System.out.println("Iniciando interface gráfica... versão " + version);
            SwingUtilities.invokeLater(() -> {
                new TelaProcessador().setVisible(true);
            });

        } else if (args[0].equalsIgnoreCase("--cli") || args[0].equalsIgnoreCase("comando")) {

            // Esperamos: --cli [diretorio_entrada] [diretorio_saida]
            if (args.length < 3) {
                System.out.println("Erro: Argumentos insuficientes para linha de comando.");
                System.out.println("Uso: java -jar Cemig2Excel.jar --cli <diretorio_entrada> <diretorio_saida>");
                return;
            }

            String entrada = args[1];
            String saida = args[2];

            System.out.println("Iniciando processamento via CLI... versão " + version);
            System.out.println("Entrada: " + entrada);
            System.out.println("Saída: " + saida);

            FaturaProcessor faturaProcessor = new FaturaProcessor(Parametro.CLI);
            faturaProcessor.processarDiretorio(entrada, saida);

            System.out.println("Processamento finalizado!");

        } else {
            System.out.println("Comando não reconhecido.");
            System.out.println("Opções: --gui | --cli <entrada> <saida>");
        }

    }

}