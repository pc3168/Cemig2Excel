package org.example.util;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.example.model.Fatura;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class ExcelExporter {


    public static void gerarCSVToString(String origem ,Fatura fatura, String nomeArquivo) {
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(nomeArquivo))) {

            // 1. Grava o cabeçalho manualmente
            writer.write("Nome Arquivo;");
            writer.write(Fatura.getCabecalhoCsv(1));
            writer.newLine();

            // 2. Grava cada fatura usando o toString() que você criou
            writer.write(origem);
            writer.write(";");
            writer.write(fatura.getLinhaCsv(1));
            writer.newLine();

            writer.flush();
            System.out.println("Arquivo CSV gerado com sucesso: " + nomeArquivo);

        } catch (IOException e) {
            System.err.println("Erro ao gerar CSV: " + e.getMessage());
        }
    }

    public static void gerarCSVToString(List<Fatura> faturas, String nomeArquivo) {
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(nomeArquivo))) {

            // 1. Grava o cabeçalho manualmente
            writer.write(Fatura.getCabecalhoCsv(1));
            writer.newLine();

            // 2. Grava cada fatura usando o toString() que você criou
            for (Fatura f : faturas) {
                writer.write(f.getLinhaCsv(1));
                writer.newLine();
            }

            writer.flush();
            System.out.println("Arquivo CSV gerado com sucesso: " + nomeArquivo);

        } catch (IOException e) {
            System.err.println("Erro ao gerar CSV: " + e.getMessage());
        }
    }
}
