package br.edu.biblioteca.structures;
import br.edu.biblioteca.model.Livro;

public class Ordenador {
    public static void ordenarLivrosPorAno(Vetor<Livro> livros) {
        int n = livros.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) 
                if (livros.get(j).getAno() > livros.get(j + 1).getAno()) {
                    Livro temp = livros.get(j);
                    livros.set(j, livros.get(j + 1));
                    livros.set(j + 1, temp);
                }
            }
        }
    }
}
