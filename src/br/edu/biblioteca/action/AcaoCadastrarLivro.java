package br.edu.biblioteca.action;

import br.edu.biblioteca.model.Exemplar;
import br.edu.biblioteca.model.Livro;
import br.edu.biblioteca.service.CatalogoService;

public class AcaoCadastrarLivro implements Acao {
    private final CatalogoService catalogoService;
    private final Livro livro;
    private final Exemplar exemplarInicial;

    public AcaoCadastrarLivro(CatalogoService catalogoService, Livro livro, Exemplar exemplarInicial) {
        this.catalogoService = catalogoService;
        this.livro = livro;
        this.exemplarInicial = exemplarInicial;
    }

    @Override
    public void executar() {
        catalogoService.cadastrarLivro(livro);
        if (exemplarInicial != null) {
            catalogoService.cadastrarExemplar(exemplarInicial);
        }
    }

    @Override
    public void desfazer() {
        if (exemplarInicial != null) {
            catalogoService.removerExemplar(exemplarInicial.getId());
        }
        catalogoService.remover(livro.getIsbn());
    }

    @Override
    public String descricao() {
        return "Cadastro do livro \"" + livro.getTitulo() + "\" (ISBN " + livro.getIsbn() + ")";
    }
}
