package org.example.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LogErro {
    private static final String PASTA_LOG = "log";
    private static final String NOME_ARQUIVO_LOG = gerarNomeArquivo();

    public static void gravarErro(String arquivoPdf, String mensagem) {
        String dataHora = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));

        try {
            Files.createDirectories(Paths.get(PASTA_LOG));
        } catch (IOException e) {
            System.err.println("Não foi possível criar a pasta de log: " + e.getMessage());
            return;
        }

        File caminhoCompleto = new File(PASTA_LOG, NOME_ARQUIVO_LOG);

        String linhaLog = String.format("[%s]: ARQUIVO: %s | ERRO: %s",
                dataHora, arquivoPdf,  mensagem);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(caminhoCompleto, true))) {
            writer.write(linhaLog);
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Erro crítico ao gravar log: " + e.getMessage());
        }
    }

    private static String gerarNomeArquivo(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
        String timestamp = LocalDateTime.now().format(formatter);
        return timestamp + ".txt";
    }
}