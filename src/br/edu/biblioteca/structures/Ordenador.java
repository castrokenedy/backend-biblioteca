package br.edu.biblioteca.structures;

import br.edu.biblioteca.model.Livro;

public class Ordenador {
    public static void ordenarLivrosPorTitulo(Vetor<Livro> livros) {
        for (int i = 0; i < livros.size() - 1; i++) {
            for (int j = 0; j < livros.size() - 1 - i; j++) {
                if (livros.get(j).getTitulo().compareToIgnoreCase(livros.get(j + 1).getTitulo()) > 0) {
                    Livro temp = livros.get(j);
                    livros.set(j, livros.get(j + 1));
                    livros.set(j + 1, temp);
                }
            }
        }
    }

    public static void ordenarLivrosPorAno(Vetor<Livro> livros) {
        for (int i = 0; i < livros.size() - 1; i++) {
            for (int j = 0; j < livros.size() - 1 - i; j++) {
                if (livros.get(j).getAno() > livros.get(j + 1).getAno()) {
                    Livro temp = livros.get(j);
                    livros.set(j, livros.get(j + 1));
                    livros.set(j + 1, temp);
                }
            }
        }
    }
}
