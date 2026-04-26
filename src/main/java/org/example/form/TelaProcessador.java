package org.example.form;

import javax.swing.*;
import java.awt.*;

public class TelaProcessador extends JFrame {

    private JTextField txtDiretorioEntrada;
    private JTextField txtDiretorioSaida;
    private JButton btnProcessar;

    public TelaProcessador() {
        setTitle("Cemig2Excel - Automação de Faturas");
        setSize(600, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centraliza a tela
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // --- Linha 1: Diretório de Entrada ---
        gbc.gridx = 0; gbc.gridy = 0;
        add(new JLabel("Pasta com PDFs:"), gbc);

        txtDiretorioEntrada = new JTextField(30);
        gbc.gridx = 1;
        add(txtDiretorioEntrada, gbc);

        JButton btnSelecionarEntrada = new JButton("Selecionar");
        gbc.gridx = 2;
        add(btnSelecionarEntrada, gbc);

        // --- Linha 2: Diretório de Saída ---
        gbc.gridx = 0; gbc.gridy = 1;
        add(new JLabel("Salvar Excel em:"), gbc);

        txtDiretorioSaida = new JTextField(30);
        gbc.gridx = 1;
        add(txtDiretorioSaida, gbc);

        JButton btnSelecionarSaida = new JButton("Selecionar");
        gbc.gridx = 2;
        add(btnSelecionarSaida, gbc);

        // --- Linha 3: Botão Processar ---
        btnProcessar = new JButton("Iniciar Processamento");
        btnProcessar.setBackground(new Color(34, 139, 34));
        btnProcessar.setForeground(Color.WHITE);
        gbc.gridx = 0; gbc.gridy = 2;
        gbc.gridwidth = 3;
        add(btnProcessar, gbc);

        // --- Eventos dos Botões ---
        btnSelecionarEntrada.addActionListener(e -> txtDiretorioEntrada.setText(selecionarPasta()));
        btnSelecionarSaida.addActionListener(e -> txtDiretorioSaida.setText(selecionarPasta()));

        btnProcessar.addActionListener(e -> {
            String entrada = txtDiretorioEntrada.getText();
            String saida = txtDiretorioSaida.getText();

            if (entrada.isEmpty() || saida.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Selecione os dois diretórios!");
                return;
            }

            // AQUI VOCÊ CHAMA SUA CLASSE PROCESSADORA
            iniciarProcessamento(entrada, saida);
        });
    }

    private String selecionarPasta() {
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int returnVal = chooser.showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            return chooser.getSelectedFile().getAbsolutePath();
        }
        return "";
    }

    private void iniciarProcessamento(String entrada, String saida) {
        try {
            // Desativa o botão para evitar cliques duplos
            btnProcessar.setEnabled(false);
            btnProcessar.setText("Processando...");

            // Sua lógica aqui:
            // FaturaProcessor processor = new FaturaProcessor();
            // processor.processar(entrada, saida);

            JOptionPane.showMessageDialog(this, "Sucesso! Arquivo gerado na pasta de saída.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro: " + ex.getMessage());
        } finally {
            btnProcessar.setEnabled(true);
            btnProcessar.setText("Iniciar Processamento");
        }
    }
}