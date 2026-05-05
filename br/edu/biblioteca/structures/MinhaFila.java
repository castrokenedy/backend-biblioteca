package br.edu.biblioteca.structures;

public class MinhaFila<T> {
    private Vetor<T> vetor = new Vetor<>();

    public void enqueue(T elemento) {
        vetor.add(elemento);
    }

    public T dequeue() {
        if (isEmpty()) return null;
        T elemento = vetor.get(0); 
        vetor.remove(0); 
        return elemento;
    }

    public T peek() {
        if (isEmpty()) return null;
        return vetor.get(0);
    }

    public boolean isEmpty() {
        return vetor.size() == 0;
    }
}
