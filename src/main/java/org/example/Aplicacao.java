package org.example;

import org.example.modelo.Tabuleiro;
import org.example.modelo.Campo;
import org.example.visao.TabuleiroConsole;

public class Aplicacao {
    public static void main(String[] args){


        Tabuleiro tabuleiro = new Tabuleiro(6, 6, 6);

        new TabuleiroConsole(tabuleiro);

    }
}
