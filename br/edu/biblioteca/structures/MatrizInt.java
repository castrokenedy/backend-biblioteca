package br.edu.biblioteca.structures;

public class MatrizInt {
    private final int[][] dados;

    public MatrizInt(int linhas, int colunas) {
        this.dados = new int[linhas][colunas];
    }

    public void set(int linha, int coluna, int valor) {
        dados[linha][coluna] = valor;
    }

    public int get(int linha, int coluna) {
        return dados[linha][coluna];
    }

    public void incrementar(int linha, int coluna) {
        dados[linha][coluna]++;
    }

    public int linhas() {
        return dados.length;
    }

    public int colunas() {
        return dados[0].length;
    }
}

