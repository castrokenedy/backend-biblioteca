package br.edu.biblioteca.structures;

public class MatrizInt {
    private int[][] dados;
    private int linhas;
    private int colunas;

    public MatrizInt(int linhas, int colunas) {
        this.linhas = linhas;
        this.colunas = colunas;
        this.dados = new int[linhas][colunas];
    }

    public void set(int linha, int coluna, int valor) {
        dados[linha][coluna] = valor;
    }

    public int get(int linha, int coluna) {
        return dados[linha][coluna];
    }

    public int getLinhas() { return linhas; }
    public int getColunas() { return colunas; }
}
