package org.example.util;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.example.model.Fatura;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class ExcelExporter {


    public static void gerarCSVToString(Fatura fatura, String nomeArquivo) {
        File arquivo = new File(nomeArquivo);
        boolean arquivoExiste = arquivo.exists();

        // StandardOpenOption.CREATE: Cria se não existir
        // StandardOpenOption.APPEND: Adiciona no final se já existir
        try (BufferedWriter writer = Files.newBufferedWriter(
                Paths.get(nomeArquivo),
                StandardOpenOption.CREATE,
                StandardOpenOption.APPEND)) {

            // 1. Só grava o cabeçalho se o arquivo for novo (não existia antes)
            if (!arquivoExiste) {
                writer.write(Fatura.getCabecalhoCsv(1));
                writer.newLine();
            }

            writer.write(fatura.getLinhaCsv(1));
            writer.newLine();

            writer.flush();
        } catch (IOException e) {
            System.err.println("Erro ao gerar CSV: " + e.getMessage());
            LogErro.gravarErro(fatura.getNomeArquivoOrigem(), "Gravação CSV, " + e.getMessage());
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
