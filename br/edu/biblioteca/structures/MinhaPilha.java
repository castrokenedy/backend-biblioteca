package br.edu.biblioteca.structures;

public class MinhaPilha<T> 
    private Vetor<T> vetor = new Vetor<>();

    public void push(T elemento) {
        vetor.add(elemento);
    }

    public T pop() {
        if (isEmpty()) return null;
        int ultimaPosicao = vetor.size() - 1;
        T elemento = vetor.get(ultimaPosicao);
        vetor.remove(ultimaPosicao);
        return elemento;
    }

    public T peek() {
        if (isEmpty()) return null;
        return vetor.get(vetor.size() - 1);
    }

    public boolean isEmpty() {
        return vetor.size() == 0;
    }
}
