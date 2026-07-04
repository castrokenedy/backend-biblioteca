package br.edu.biblioteca.service;

import br.edu.biblioteca.model.Exemplar;
import br.edu.biblioteca.model.Livro;
import br.edu.biblioteca.structures.ArvoreBST;
import br.edu.biblioteca.structures.Ordenador;
import br.edu.biblioteca.structures.Vetor;

public class CatalogoService {
    private final Vetor<Livro> livros = new Vetor<>();
    private final Vetor<Exemplar> exemplares = new Vetor<>();
    private final ArvoreBST<String, Livro> indicePorIsbn = new ArvoreBST<>();
<<<<<<< HEAD
    private int sequenciaExemplar = 1;
=======
>>>>>>> 24cefdf593e0554ca034471d6c0481e0de4dbfcb

    public void cadastrarLivro(Livro livro) {
        livros.add(livro);
        indicePorIsbn.put(livro.getIsbn(), livro);
    }

    public void cadastrarExemplar(Exemplar exemplar) {
        exemplares.add(exemplar);
<<<<<<< HEAD
        if (exemplar.getId() >= sequenciaExemplar) {
            sequenciaExemplar = exemplar.getId() + 1;
        }
    }

    public int proximoIdExemplar() {
        return sequenciaExemplar;
    }

    public boolean removerExemplar(int id) {
        for (int i = 0; i < exemplares.size(); i++) {
            if (exemplares.get(i).getId() == id) {
                exemplares.remove(i);
                return true;
            }
        }
        return false;
=======
>>>>>>> 24cefdf593e0554ca034471d6c0481e0de4dbfcb
    }

    public boolean remover(String isbn) {
        for (int i = 0; i < livros.size(); i++) {
            if (livros.get(i).getIsbn().equals(isbn)) {
                livros.remove(i);
                indicePorIsbn.remove(isbn);
                return true;
            }
        }
        return false;
    }

    public Livro buscar(String isbn) {
        return indicePorIsbn.get(isbn);
    }

    public Vetor<Livro> listar() {
        return livros;
    }

    public Vetor<Livro> listarOrdenadoPorTitulo() {
        Vetor<Livro> copia = copiarLivros();
        Ordenador.ordenarLivrosPorTitulo(copia);
        return copia;
    }

    public Vetor<Exemplar> getExemplares() {
        return exemplares;
    }

    public Exemplar buscarExemplarPorId(int id) {
        for (int i = 0; i < exemplares.size(); i++) {
            if (exemplares.get(i).getId() == id) {
                return exemplares.get(i);
            }
        }
        return null;
    }

    public Vetor<Exemplar> buscarExemplaresPorIsbn(String isbn) {
        Vetor<Exemplar> encontrados = new Vetor<>();
        for (int i = 0; i < exemplares.size(); i++) {
            if (exemplares.get(i).getIsbnLivro().equals(isbn)) {
                encontrados.add(exemplares.get(i));
            }
        }
        return encontrados;
    }

    private Vetor<Livro> copiarLivros() {
        Vetor<Livro> copia = new Vetor<>();
        for (int i = 0; i < livros.size(); i++) {
            copia.add(livros.get(i));
        }
        return copia;
    }
}
