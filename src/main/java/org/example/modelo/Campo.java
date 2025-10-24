package org.example.modelo;

import org.example.excecao.Explosao;


import java.util.ArrayList;
import java.util.List;

public class Campo {
    private final int linha;
    private final int coluna;
    private boolean minado = false;
    private boolean aberto = false;
    private boolean marcado = false;

    private List<Campo> vizinhos = new ArrayList<>();
    public Campo(int linha, int coluna){
        this.linha = linha;
        this.coluna = coluna;
    }

    public boolean adicionarVizinho(Campo vizinho){
        boolean linhaDiferente = linha != vizinho.linha;
        boolean colunaDIferente = coluna != vizinho.coluna;
        boolean diagonal = linhaDiferente && colunaDIferente;

        int deltaLinha = Math.abs(linha - vizinho.linha);
        int deltaColuna = Math.abs(coluna - vizinho.coluna);
        int deltaGeral = deltaColuna + deltaLinha;

        if(deltaGeral ==1 && !diagonal){
            vizinhos.add(vizinho);
            return true;
        } else if(deltaGeral == 2 && diagonal){
            vizinhos.add(vizinho);
            return true;
        } else {
            return false;
        }
    }

    public void alternarMarcacao(){
        if(!aberto){
            marcado = !marcado;

        }
    }

    public boolean abrir(){
        if(!aberto && !marcado){
            aberto = true;

            if(minado){
                throw new Explosao();


            }

            if(vizinhacaSegura()){
                vizinhos.forEach(Campo::abrir);

            }

            return true;
        } else{
            return false;
        }
    }

boolean vizinhacaSegura(){
        return vizinhos.stream().noneMatch(v -> v.minado);
}


public void minar() {
    if (!minado) {


        minado = true;
    }

}

public boolean isMinado(){
   return minado;
}


public  boolean isMarcado(){
        return marcado;
}

public boolean isAberto(){

        return isAberto();

}

    public int getColuna() {
        return coluna;
    }

    public int getLinha() {
        return linha;
    }

    boolean objetivoAlcancado(){
        boolean desvendado = !minado && aberto;
        boolean protegido = minado && marcado;
        return desvendado || protegido;


    }

    long minasNavizinhaca(){
        return vizinhos.stream().filter(v -> v.minado).count();

    }

    void reiniciar(){
        aberto = false;
        minado = false;
        marcado = false;
    }

    public String toString(){
        if(marcado){
            return "x";
        } else if(
                aberto && minado
        ){
            return "*";
        }

        else if(aberto && minasNavizinhaca()> 0){
            return Long.toString(minasNavizinhaca());
        } else if(aberto){
            return "";
        } else {
            return "?";
        }
    }
}
