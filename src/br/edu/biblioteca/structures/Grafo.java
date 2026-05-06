package br.edu.biblioteca.structures;

public class Grafo<T> {
    private final Vetor<T> vertices = new Vetor<>();
    private final Vetor<Vetor<Integer>> adjacencias = new Vetor<>();

    public void adicionarVertice(T valor) {
        vertices.add(valor);
        adjacencias.add(new Vetor<>());
    }

    public void adicionarAresta(int origem, int destino) {
        adjacencias.get(origem).add(destino);
    }

    public Vetor<Integer> vizinhos(int indice) {
        return adjacencias.get(indice);
    }

    public T getVertice(int indice) {
        return vertices.get(indice);
    }

    public int quantidadeVertices() {
        return vertices.size();
    }
}

