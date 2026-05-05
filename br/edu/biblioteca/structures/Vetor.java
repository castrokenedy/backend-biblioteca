package br.edu.biblioteca.structures;

public class Vetor<T> {
    private T[] elementos;
    private int tamanho;

    @SuppressWarnings("unchecked")
    public Vetor(int capacidade) {
        this.elementos = (T[]) new Object[capacidade];
        this.tamanho = 0;
    }

    public Vetor() {
        this(10);
    }

    public boolean add(T elemento) {
        garantirCapacidade();
        this.elementos[this.tamanho] = elemento;
        this.tamanho++;
        return true;
    }

    public T get(int posicao) {
        if (!posicaoValida(posicao)) {
            throw new IllegalArgumentException("Posição inválida");
        }
        return this.elementos[posicao];
    }

    public void set(int posicao, T elemento) {
        if (!posicaoValida(posicao)) {
            throw new IllegalArgumentException("Posição inválida");
        }
        this.elementos[posicao] = elemento;
    }

    public void remove(int posicao) {
        if (!posicaoValida(posicao)) {
            throw new IllegalArgumentException("Posição inválida");
        }
        for (int i = posicao; i < this.tamanho - 1; i++) {
            this.elementos[i] = this.elementos[i + 1];
        }
        this.tamanho--;
        this.elementos[tamanho] = null;
    }

    public int size() {
        return this.tamanho;
    }

    private boolean posicaoValida(int posicao) {
        return posicao >= 0 && posicao < tamanho;
    }

    @SuppressWarnings("unchecked")
    private void garantirCapacidade() {
        if (this.tamanho == this.elementos.length) {
            T[] elementosNovos = (T[]) new Object[this.elementos.length * 2];
            for (int i = 0; i < this.elementos.length; i++) {
                elementosNovos[i] = this.elementos[i];
            }
            this.elementos = elementosNovos;
        }
    }
}
