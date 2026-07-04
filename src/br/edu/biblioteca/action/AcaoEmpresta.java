package br.edu.biblioteca.action;

import br.edu.biblioteca.model.Emprestimo;
import br.edu.biblioteca.service.EmprestimoService;

public class AcaoEmpresta implements Acao {
    private final EmprestimoService emprestimoService;
    private final int usuarioId;
    private final int exemplarId;
    private Emprestimo emprestimoRealizado;

    public AcaoEmpresta(EmprestimoService emprestimoService, int usuarioId, int exemplarId) {
        this.emprestimoService = emprestimoService;
        this.usuarioId = usuarioId;
        this.exemplarId = exemplarId;
    }

    @Override
    public void executar() {
        emprestimoRealizado = emprestimoService.emprestarExemplar(usuarioId, exemplarId);
    }

    @Override
    public void desfazer() {
        if (emprestimoRealizado != null) {
            emprestimoService.cancelarEmprestimo(emprestimoRealizado.getId());
        }
    }

    @Override
    public String descricao() {
        return "Empréstimo do exemplar " + exemplarId + " para o usuário " + usuarioId;
    }

    public boolean foiRealizadoComSucesso() {
        return emprestimoRealizado != null;
    }

    public Emprestimo getEmprestimoRealizado() {
        return emprestimoRealizado;
    }
}
