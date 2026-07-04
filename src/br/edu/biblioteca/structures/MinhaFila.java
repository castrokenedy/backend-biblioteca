package br.edu.biblioteca.structures;

public class MinhaFila<T> {
    private final Vetor<T> elementos = new Vetor<>();

    public void enqueue(T valor) {
        elementos.add(valor);
    }

    public T dequeue() {
        if (isEmpty()) {
            return null;
        }
        return elementos.remove(0);
    }

    public T peek() {
        if (isEmpty()) {
            return null;
        }
        return elementos.get(0);
    }

    public boolean isEmpty() {
        return elementos.isEmpty();
    }

    public int size() {
        return elementos.size();
    }
}

