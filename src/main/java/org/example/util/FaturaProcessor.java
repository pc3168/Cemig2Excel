package org.example.util;

import org.example.model.Fatura;
import org.example.model.FaturaBruta;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class FaturaProcessor {

    public void processarDiretorio(String path) {
        List<Fatura> faturasProcessadas = new ArrayList<>();
        File pasta = new File(path);

        for (File f : pasta.listFiles((dir, name) -> name.toLowerCase().endsWith(".pdf"))) {

            LerPdfParser leitor = new LerPdfParser(f);
            leitor.lerPdf();

                // Transfere os dados para o DTO (objetos menores)
            FaturaBruta dados = new FaturaBruta(
                    leitor.cabecalho(),
                    leitor.valoresFaturados(),
                    leitor.historicoConsumo(),
                    leitor.reservadoFisco(),
                    leitor.dadosPessoais(),
                    leitor.unidadeConsumidora(),
                    leitor.referenteVencimento(),
                    leitor.informacoesGerais(),
                    leitor.informacoesTecnicas()
            );


            FaturaParser faturaParser = new FaturaParser(dados);
            Fatura faturaLimpa = faturaParser.getFatura();
            faturasProcessadas.add(faturaLimpa);
            System.out.println("=================================");
            System.out.println(faturaLimpa);
            System.out.println("=================================");

        }
        ExcelExporter.gerarCSVToString(faturasProcessadas, "saida/" + gerarNomeArquivo());
    }

    private String gerarNomeArquivo(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
        String timestamp = LocalDateTime.now().format(formatter);
        return timestamp + ".csv";
    }
}
