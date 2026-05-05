package br.edu.biblioteca.structures;

public class MinhaPilha<T> {
    private class No {
        T dado;
        No proximo;
        No(T dado) { this.dado = dado; }
    }

    private No topo;

    public void push(T elemento) {
        No novoNo = new No(elemento);
        novoNo.proximo = topo;
        topo = novoNo;
    }

    public T pop() {
        if (isEmpty()) return null;
        T dado = topo.dado;
        topo = topo.proximo;
        return dado;
    }

    public T peek() {
        if (isEmpty()) return null;
        return topo.dado;
    }

    public boolean isEmpty() {
        return topo == null;
    }
}
