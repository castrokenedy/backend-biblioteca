package br.edu.biblioteca.structures;

public class ArvoreBST<K extends Comparable<K>, V> {
    private class No {
        K chave;
        V valor;
        No esquerda;
        No direita;

        No(K chave, V valor) {
            this.chave = chave;
            this.valor = valor;
        }
    }

    private No raiz;

    public void put(K chave, V valor) {
        raiz = put(raiz, chave, valor);
    }

    private No put(No atual, K chave, V valor) {
        if (atual == null) return new No(chave, valor);
        int cmp = chave.compareTo(atual.chave);
        if (cmp < 0) atual.esquerda = put(atual.esquerda, chave, valor);
        else if (cmp > 0) atual.direita = put(atual.direita, chave, valor);
        else atual.valor = valor;
        return atual;
    }

    public V get(K chave) {
        No atual = raiz;
        while (atual != null) {
            int cmp = chave.compareTo(atual.chave);
            if (cmp == 0) return atual.valor;
            atual = cmp < 0 ? atual.esquerda : atual.direita;
        }
        return null;
    }

    public void remove(K chave) {
        raiz = remove(raiz, chave);
    }

    private No remove(No atual, K chave) {
        if (atual == null) return null;
        int cmp = chave.compareTo(atual.chave);
        if (cmp < 0) {
            atual.esquerda = remove(atual.esquerda, chave);
        } else if (cmp > 0) {
            atual.direita = remove(atual.direita, chave);
        } else {
            if (atual.esquerda == null) return atual.direita;
            if (atual.direita == null) return atual.esquerda;
            No sucessor = menor(atual.direita);
            atual.chave = sucessor.chave;
            atual.valor = sucessor.valor;
            atual.direita = remove(atual.direita, sucessor.chave);
        }
        return atual;
    }

    private No menor(No no) {
        while (no.esquerda != null) no = no.esquerda;
        return no;
    }

    public Vetor<V> inOrder() {
        Vetor<V> resultado = new Vetor<>();
        inOrder(raiz, resultado);
        return resultado;
    }

    private void inOrder(No no, Vetor<V> resultado) {
        if (no != null) {
            inOrder(no.esquerda, resultado);
            resultado.add(no.valor);
            inOrder(no.direita, resultado);
        }
    }
}
