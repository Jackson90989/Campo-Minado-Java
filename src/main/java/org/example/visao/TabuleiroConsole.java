package org.example.visao;

import org.example.excecao.Explosao;
import org.example.excecao.sairException;
import org.example.modelo.Tabuleiro;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class TabuleiroConsole {
    private Tabuleiro tabuleiro;
    private Scanner entrada = new Scanner(System.in);

    public TabuleiroConsole(Tabuleiro tabuleiro) {
        this.tabuleiro = tabuleiro;
        executarJogo();
    }

    private void executarJogo() {
        try {
            boolean continuar = true;
            while (continuar) {
                cicloDoJogo();
                System.out.println("Outra partida (S/n): ");
                String resposta = entrada.nextLine();
                if ("n".equalsIgnoreCase(resposta)) {
                    continuar = false;
                } else {
                    tabuleiro.reiniciar();
                }
            }
        } catch (sairException e) {
            System.out.println("Tchau!");
        } finally {
            entrada.close();
        }
    }

    private void cicloDoJogo() {
        try {
            while (!tabuleiro.objetivoAlcancado()) {
                System.out.println(tabuleiro);

                String digitado = capturaValorDigitado("Digite (x, y): ");

                // Validação melhorada das coordenadas
                List<Integer> coordenadas = validarEProcessarCoordenadas(digitado);
                if (coordenadas == null) {
                    continue; // Volta para o início do loop se as coordenadas forem inválidas
                }

                int x = coordenadas.get(0);
                int y = coordenadas.get(1);

                // Verifica se as coordenadas estão dentro do tabuleiro
                if (!tabuleiro.coordenadaValida(x, y)) {
                    System.out.println("Coordenadas fora do tabuleiro! Use valores entre 0 e " +
                            (tabuleiro.getLinhas()-1) + " para linha e 0 e " +
                            (tabuleiro.getColunas()-1) + " para coluna.");
                    continue;
                }

                digitado = capturaValorDigitado("1 - Abrir ou 2 - (Des)Marcar: ");
                if ("1".equals(digitado)) {
                    tabuleiro.abrir(x, y);
                } else if ("2".equals(digitado)) {
                    tabuleiro.alterarMarcacao(x, y);
                } else {
                    System.out.println("Opção inválida! Digite 1 ou 2.");
                }

            }
            System.out.println(tabuleiro);
            System.out.println("Você ganhou!!!");
        } catch (Explosao e) {
            System.out.println(tabuleiro);
            System.out.println("Você perdeu!");
        }
    }

    private List<Integer> validarEProcessarCoordenadas(String input) {
        // Verifica se o input contém vírgula
        if (!input.contains(",")) {
            System.out.println("Formato inválido! Use: x,y (exemplo: 2,3)");
            return null;
        }

        String[] partes = input.split(",");

        // Verifica se há exatamente 2 partes
        if (partes.length != 2) {
            System.out.println("Digite exatamente duas coordenadas separadas por vírgula!");
            return null;
        }

        List<Integer> coordenadas = new ArrayList<>();

        try {
            for (String parte : partes) {
                String parteTrim = parte.trim();
                // Verifica se a parte não está vazia
                if (parteTrim.isEmpty()) {
                    System.out.println("Coordenada vazia! Digite números para ambas as coordenadas.");
                    return null;
                }
                coordenadas.add(Integer.parseInt(parteTrim));
            }
            return coordenadas;

        } catch (NumberFormatException e) {
            System.out.println("Digite números válidos para as coordenadas! (exemplo: 2,3)");
            return null;
        }
    }

    private String capturaValorDigitado(String texto) {
        System.out.print(texto);
        String digitado = entrada.nextLine();

        if ("sair".equalsIgnoreCase(digitado)) {
            throw new sairException();
        }

        return digitado;
    }
}