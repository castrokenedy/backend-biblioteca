package br.edu.biblioteca.structures;

@SuppressWarnings("unchecked")
public class Vetor<T> {
    private T[] elementos;
    private int size;

    public Vetor() {
        this(10);
    }

    public Vetor(int capacidadeInicial) {
        this.elementos = (T[]) new Object[capacidadeInicial];
        this.size = 0;
    }

    public void add(T elemento) {
        garantirCapacidade();
        elementos[size++] = elemento;
    }

    public T get(int indice) {
        validarIndice(indice);
        return elementos[indice];
    }

    public void set(int indice, T elemento) {
        validarIndice(indice);
        elementos[indice] = elemento;
    }

    public T remove(int indice) {
        validarIndice(indice);
        T removido = elementos[indice];
        for (int i = indice; i < size - 1; i++) {
            elementos[i] = elementos[i + 1];
        }
        elementos[--size] = null;
        return removido;
    }

    public boolean removeElemento(T elemento) {
        int indice = indexOf(elemento);
        if (indice >= 0) {
            remove(indice);
            return true;
        }
        return false;
    }

    public int indexOf(T elemento) {
        for (int i = 0; i < size; i++) {
            if ((elementos[i] == null && elemento == null) || (elementos[i] != null && elementos[i].equals(elemento))) {
                return i;
            }
        }
        return -1;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    private void garantirCapacidade() {
        if (size == elementos.length) {
            T[] novo = (T[]) new Object[elementos.length * 2];
            for (int i = 0; i < elementos.length; i++) {
                novo[i] = elementos[i];
            }
            elementos = novo;
        }
    }

    private void validarIndice(int indice) {
        if (indice < 0 || indice >= size) {
            throw new IndexOutOfBoundsException("Índice inválido: " + indice);
        }
    }
}

