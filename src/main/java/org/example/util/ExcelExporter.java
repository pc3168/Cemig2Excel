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

    public static void gerarCSV(List<Fatura> faturas, String nomeArquivo) {
        try (
                BufferedWriter writer = Files.newBufferedWriter(Paths.get(nomeArquivo));
                CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT
                        .withDelimiter(';') // Facilita abrir no Excel brasileiro
                        .withHeader("Chave", "data emissao"))
        ) {
            for (Fatura f : faturas) {
                csvPrinter.printRecord(
                        f.getChaveAcesso(),
                        f.getDataEmissao()
                );
            }

            csvPrinter.flush();
            System.out.println("Arquivo CSV gerado com sucesso: " + nomeArquivo);

        } catch (IOException e) {
            System.err.println("Erro ao gerar CSV: " + e.getMessage());
        }
    }
}
