package br.edu.biblioteca.structures;

public class MinhaFila<T> {
    private class No {
        T dado;
        No proximo;
        No(T dado) { this.dado = dado; }
    }

    private No inicio;
    private No fim;

    public void enqueue(T elemento) {
        No novoNo = new No(elemento);
        if (isEmpty()) {
            inicio = novoNo;
        } else {
            fim.proximo = novoNo;
        }
        fim = novoNo;
    }

    public T dequeue() {
        if (isEmpty()) return null;
        T dado = inicio.dado;
        inicio = inicio.proximo;
        if (inicio == null) {
            fim = null;
        }
        return dado;
    }

    public T peek() {
        if (isEmpty()) return null;
        return inicio.dado;
    }

    public boolean isEmpty() {
        return inicio == null;
    }
}
