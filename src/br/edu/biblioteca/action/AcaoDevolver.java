package br.edu.biblioteca.action;

import br.edu.biblioteca.model.Emprestimo;
import br.edu.biblioteca.model.StatusEmprestimo;
import br.edu.biblioteca.service.EmprestimoService;

public class AcaoDevolver implements Acao {
    private final EmprestimoService emprestimoService;
    private final int emprestimoId;
    private StatusEmprestimo statusAnterior;
    private boolean sucesso;

    public AcaoDevolver(EmprestimoService emprestimoService, int emprestimoId) {
        this.emprestimoService = emprestimoService;
        this.emprestimoId = emprestimoId;
    }

    @Override
    public void executar() {
        Emprestimo emprestimo = emprestimoService.buscarPorId(emprestimoId);
        if (emprestimo != null) {
            statusAnterior = emprestimo.getStatus();
        }
        sucesso = emprestimoService.devolverExemplar(emprestimoId);
    }

    @Override
    public void desfazer() {
        if (sucesso) {
            emprestimoService.desfazerDevolucao(emprestimoId, statusAnterior);
        }
    }

    @Override
    public String descricao() {
        return "Devolução do empréstimo " + emprestimoId;
    }

    public boolean foiRealizadoComSucesso() {
        return sucesso;
    }
}
