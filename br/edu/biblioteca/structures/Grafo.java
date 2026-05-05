package br.edu.biblioteca.structures;

public class Grafo<T> {
    private Vetor<T> vertices;
    private Vetor<Vetor<Integer>> adjacencias;

    public Grafo() {
        vertices = new Vetor<>();
        adjacencias = new Vetor<>();
    }

    public void addVertice(T dado) {
        vertices.add(dado);
        adjacencias.add(new Vetor<>());
    }

    public void addAresta(int origem, int destino) {
        adjacencias.get(origem).add(destino);
        adjacencias.get(destino).add(origem);
    }
}
