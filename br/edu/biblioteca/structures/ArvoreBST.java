package br.edu.biblioteca.structures;

public class ArvoreBST<K extends Comparable<K>, V> {
    private class No {
        K chave;
        V valor;
        No esquerdo, direito;

        No(K chave, V valor) {
            this.chave = chave;
            this.valor = valor;
        }
    }

    private No raiz;

    public void put(K chave, V valor) {
        raiz = put(raiz, chave, valor);
    }

    private No put(No no, K chave, V valor) {
        if (no == null) return new No(chave, valor);
        int cmp = chave.compareTo(no.chave);
        if (cmp < 0) no.esquerdo = put(no.esquerdo, chave, valor);
        else if (cmp > 0) no.direito = put(no.direito, chave, valor);
        else no.valor = valor; 
        return no;
    }

    public V get(K chave) {
        No no = get(raiz, chave);
        return no == null ? null : no.valor;
    }

    private No get(No no, K chave) {
        if (no == null) return null;
        int cmp = chave.compareTo(no.chave);
        if (cmp < 0) return get(no.esquerdo, chave);
        else if (cmp > 0) return get(no.direito, chave);
        else return no;
    }

    public void remove(K chave) {
        raiz = remove(raiz, chave);
    }

    private No remove(No no, K chave) {
        if (no == null) return null;
        int cmp = chave.compareTo(no.chave);
        if (cmp < 0) {
            no.esquerdo = remove(no.esquerdo, chave);
        } else if (cmp > 0) {
            no.direito = remove(no.direito, chave);
        } else {
            if (no.direito == null) return no.esquerdo;
            if (no.esquerdo == null) return no.direito;
            No t = no;
            no = min(t.direito);
            no.direito = deleteMin(t.direito);
            no.esquerdo = t.esquerdo;
        }
        return no;
    }

    private No min(No no) {
        if (no.esquerdo == null) return no;
        else return min(no.esquerdo);
    }

    private No deleteMin(No no) {
        if (no.esquerdo == null) return no.direito;
        no.esquerdo = deleteMin(no.esquerdo);
        return no;
    }

    public void inOrder(Vetor<V> lista) {
        inOrder(raiz, lista);
    }

    private void inOrder(No no, Vetor<V> lista) {
        if (no == null) return;
        inOrder(no.esquerdo, lista);
        lista.add(no.valor);
        inOrder(no.direito, lista);
    }
}
