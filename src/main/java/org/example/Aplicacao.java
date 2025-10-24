package org.example;

import org.example.modelo.Tabuleiro;
import org.example.modelo.Campo;

public class Aplicacao {
    public static void main(String[] args){
        Tabuleiro tabuleiro = new Tabuleiro(6, 6, 6);
        tabuleiro.abrir(3,3);
        tabuleiro.alterarMarcacao(4,5);
        tabuleiro.alterarMarcacao(3,5);
        System.out.println(tabuleiro);
    }
}
