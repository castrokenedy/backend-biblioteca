package br.edu.biblioteca.structures;

public class MinhaPilha<T> {
    private final Vetor<T> elementos = new Vetor<>();

    public void push(T valor) {
        elementos.add(valor);
    }

    public T pop() {
        if (isEmpty()) {
            return null;
        }
        return elementos.remove(elementos.size() - 1);
    }

    public T peek() {
        if (isEmpty()) {
            return null;
        }
        return elementos.get(elementos.size() - 1);
    }

    public boolean isEmpty() {
        return elementos.isEmpty();
    }

    public int size() {
        return elementos.size();
    }
}

