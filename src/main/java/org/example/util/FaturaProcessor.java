package org.example.util;

import org.example.model.Fatura;
import org.example.model.FaturaBruta;
import org.example.model.Parametro;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class FaturaProcessor {

    private Parametro parametro;

    public FaturaProcessor(Parametro parametro) {
        this.parametro = parametro;
    }

    private static final String NOME_ARQUIVO_CSV = gerarNomeArquivo();

    public void processarDiretorio(String origem, String destino) {
        File pasta = new File(origem);

        for (File f : pasta.listFiles((dir, name) -> name.toLowerCase().endsWith(".pdf"))) {
            //if (!f.getName().equals("21042026140947.pdf")) continue;
            try{
                LerPdfParser leitor = new LerPdfParser(f);
                leitor.lerPdf();

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


                FaturaParser faturaParser = new FaturaParser(dados, f.getName());
                Fatura faturaLimpa = faturaParser.getFatura();
                if (parametro.getParametro().equalsIgnoreCase("--cli")){
                    System.out.println("=================================");
                    System.out.println(faturaLimpa);
                    System.out.println("=================================");
                }

                ExcelExporter.gerarCSVToString(faturaLimpa, destino + File.separator + NOME_ARQUIVO_CSV);
            }catch (Exception e){
                LogErro.gravarErro(f.getName(), e.getMessage());
            }

        }
    }

    private static String gerarNomeArquivo(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
        String timestamp = LocalDateTime.now().format(formatter);
        return timestamp + ".csv";
    }
}
