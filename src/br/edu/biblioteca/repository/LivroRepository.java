package br.edu.biblioteca.repository;

import br.edu.biblioteca.model.Autor;
import br.edu.biblioteca.model.Categoria;
import br.edu.biblioteca.model.Livro;
import br.edu.biblioteca.structures.Vetor;

/**
 * Persiste livros em CSV. Listas internas (autores, categorias, palavras-chave)
 * são serializadas em um único campo, separadas por "|".
 * Formato: isbn;titulo;ano;autores;categorias;palavrasChave;totalEmprestimos
 */
public class LivroRepository {
    private static final String CAMINHO = "data/livros.csv";

    public void salvar(Vetor<Livro> livros) {
        Vetor<String> linhas = new Vetor<>();
        for (int i = 0; i < livros.size(); i++) {
            linhas.add(paraLinha(livros.get(i)));
        }
        FileStorage.salvar(CAMINHO, linhas);
    }

    public Vetor<Livro> carregar() {
        Vetor<Livro> livros = new Vetor<>();
        Vetor<String> linhas = FileStorage.carregar(CAMINHO);
        for (int i = 0; i < linhas.size(); i++) {
            livros.add(paraLivro(linhas.get(i)));
        }
        return livros;
    }

    private String paraLinha(Livro livro) {
        return livro.getIsbn() + ";" +
                livro.getTitulo() + ";" +
                livro.getAno() + ";" +
                juntarAutores(livro) + ";" +
                juntarCategorias(livro) + ";" +
                juntarPalavras(livro) + ";" +
                livro.getTotalEmprestimos();
    }

    private String juntarAutores(Livro livro) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < livro.getAutores().size(); i++) {
            Autor autor = livro.getAutores().get(i);
            sb.append(autor.getId()).append(":").append(autor.getNome());
            if (i < livro.getAutores().size() - 1) sb.append("|");
        }
        return sb.toString();
    }

    private String juntarCategorias(Livro livro) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < livro.getCategorias().size(); i++) {
            Categoria categoria = livro.getCategorias().get(i);
            sb.append(categoria.getId()).append(":").append(categoria.getNome());
            if (i < livro.getCategorias().size() - 1) sb.append("|");
        }
        return sb.toString();
    }

    private String juntarPalavras(Livro livro) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < livro.getPalavrasChave().size(); i++) {
            sb.append(livro.getPalavrasChave().get(i));
            if (i < livro.getPalavrasChave().size() - 1) sb.append("|");
        }
        return sb.toString();
    }

    private Livro paraLivro(String linha) {
        String[] campos = linha.split(";", -1);
        Livro livro = new Livro(campos[0], campos[1], Integer.parseInt(campos[2]));

        if (campos.length > 3 && !campos[3].isEmpty()) {
            for (String parte : campos[3].split("\\|")) {
                String[] dados = parte.split(":", 2);
                livro.getAutores().add(new Autor(Integer.parseInt(dados[0]), dados[1]));
            }
        }
        if (campos.length > 4 && !campos[4].isEmpty()) {
            for (String parte : campos[4].split("\\|")) {
                String[] dados = parte.split(":", 2);
                livro.getCategorias().add(new Categoria(Integer.parseInt(dados[0]), dados[1]));
            }
        }
        if (campos.length > 5 && !campos[5].isEmpty()) {
            for (String palavra : campos[5].split("\\|")) {
                livro.getPalavrasChave().add(palavra);
            }
        }
        if (campos.length > 6 && !campos[6].isEmpty()) {
            livro.setTotalEmprestimos(Integer.parseInt(campos[6]));
        }
        return livro;
    }
}
