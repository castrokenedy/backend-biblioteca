package br.edu.biblioteca.action;

import br.edu.biblioteca.model.Livro;
import br.edu.biblioteca.service.CatalogoService;

public class AcaoRemoverLivro implements Acao {
    private final CatalogoService catalogoService;
    private final String isbn;
    private Livro livroRemovido;

    public AcaoRemoverLivro(CatalogoService catalogoService, String isbn) {
        this.catalogoService = catalogoService;
        this.isbn = isbn;
    }

    @Override
    public void executar() {
        livroRemovido = catalogoService.buscar(isbn);
        catalogoService.remover(isbn);
    }

    @Override
    public void desfazer() {
        if (livroRemovido != null) {
            catalogoService.cadastrarLivro(livroRemovido);
        }
    }

    @Override
    public String descricao() {
        return "Remoção do livro ISBN " + isbn;
    }

    public Livro getLivroRemovido() {
        return livroRemovido;
    }
}
