package br.edu.biblioteca.service;

import br.edu.biblioteca.model.*;
import br.edu.biblioteca.structures.Vetor;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class EmprestimoService {
    private final Vetor<Emprestimo> emprestimos = new Vetor<>();
    private final Vetor<Multa> multas = new Vetor<>();
    private final CatalogoService catalogoService;
    private final UsuarioService usuarioService;
    private int sequenciaEmprestimo = 1;
    private int sequenciaMulta = 1;

    public EmprestimoService(CatalogoService catalogoService, UsuarioService usuarioService) {
        this.catalogoService = catalogoService;
        this.usuarioService = usuarioService;
    }

    public Emprestimo emprestarExemplar(int usuarioId, int exemplarId) {
        Usuario usuario = usuarioService.buscarPorId(usuarioId);
        Exemplar exemplar = catalogoService.buscarExemplarPorId(exemplarId);

        if (usuario == null || usuario.isBloqueado() || exemplar == null || exemplar.getStatus() != StatusExemplar.DISPONIVEL) {
            return null;
        }

        Emprestimo emprestimo = new Emprestimo(
                sequenciaEmprestimo++,
                usuarioId,
                exemplarId,
                LocalDate.now(),
                LocalDate.now().plusDays(7),
                StatusEmprestimo.ATIVO
        );

        exemplar.setStatus(StatusExemplar.EMPRESTADO);
        Livro livro = catalogoService.buscar(exemplar.getIsbnLivro());
        if (livro != null) {
            livro.incrementarEmprestimos();
        }
        emprestimos.add(emprestimo);
        return emprestimo;
    }

    public boolean devolverExemplar(int emprestimoId) {
        Emprestimo emprestimo = buscarPorId(emprestimoId);
        if (emprestimo == null || emprestimo.getStatus() == StatusEmprestimo.DEVOLVIDO) {
            return false;
        }

        emprestimo.setDataDevolucao(LocalDate.now());
        long atraso = ChronoUnit.DAYS.between(emprestimo.getDataPrevista(), emprestimo.getDataDevolucao());
        if (atraso > 0) {
            emprestimo.setStatus(StatusEmprestimo.ATRASADO);
            multas.add(new Multa(sequenciaMulta++, emprestimo.getId(), atraso * 2.5, (int) atraso, false));
        } else {
            emprestimo.setStatus(StatusEmprestimo.DEVOLVIDO);
        }

        Exemplar exemplar = catalogoService.buscarExemplarPorId(emprestimo.getExemplarId());
        if (exemplar != null) {
            exemplar.setStatus(StatusExemplar.DISPONIVEL);
        }
        return true;
    }

    public boolean renovar(int emprestimoId) {
        Emprestimo emprestimo = buscarPorId(emprestimoId);
        if (emprestimo != null && (emprestimo.getStatus() == StatusEmprestimo.ATIVO || emprestimo.getStatus() == StatusEmprestimo.RENOVADO)) {
            emprestimo.setDataPrevista(emprestimo.getDataPrevista().plusDays(7));
            emprestimo.setStatus(StatusEmprestimo.RENOVADO);
            return true;
        }
        return false;
    }

    public double calcularMulta(int emprestimoId) {
        for (int i = 0; i < multas.size(); i++) {
            if (multas.get(i).getEmprestimoId() == emprestimoId) {
                return multas.get(i).getValor();
            }
        }
        return 0.0;
    }

    public Emprestimo buscarPorId(int id) {
        for (int i = 0; i < emprestimos.size(); i++) {
            if (emprestimos.get(i).getId() == id) {
                return emprestimos.get(i);
            }
        }
        return null;
    }

    public Vetor<Emprestimo> listar() {
        return emprestimos;
    }

    public Vetor<Multa> listarMultas() {
        return multas;
    }

    /** Usado pela camada de persistência para repopular o serviço ao iniciar o sistema. */
    public void carregarEmprestimo(Emprestimo emprestimo) {
        emprestimos.add(emprestimo);
        if (emprestimo.getId() >= sequenciaEmprestimo) {
            sequenciaEmprestimo = emprestimo.getId() + 1;
        }
    }

    /** Desfaz um empréstimo recém-realizado (Undo da Acao AcaoEmpresta): remove o registro e libera o exemplar. */
    public boolean cancelarEmprestimo(int emprestimoId) {
        Emprestimo emprestimo = buscarPorId(emprestimoId);
        if (emprestimo == null) {
            return false;
        }
        emprestimos.removeElemento(emprestimo);

        Exemplar exemplar = catalogoService.buscarExemplarPorId(emprestimo.getExemplarId());
        if (exemplar != null) {
            exemplar.setStatus(StatusExemplar.DISPONIVEL);
            Livro livro = catalogoService.buscar(exemplar.getIsbnLivro());
            if (livro != null) {
                livro.decrementarEmprestimos();
            }
        }
        removerMultaDoEmprestimo(emprestimoId);
        return true;
    }

    /** Desfaz uma devolução (Undo da Acao AcaoDevolver): reabre o empréstimo e reoccupa o exemplar. */
    public boolean desfazerDevolucao(int emprestimoId, StatusEmprestimo statusAnterior) {
        Emprestimo emprestimo = buscarPorId(emprestimoId);
        if (emprestimo == null) {
            return false;
        }
        emprestimo.setDataDevolucao(null);
        emprestimo.setStatus(statusAnterior != null ? statusAnterior : StatusEmprestimo.ATIVO);
        removerMultaDoEmprestimo(emprestimoId);

        Exemplar exemplar = catalogoService.buscarExemplarPorId(emprestimo.getExemplarId());
        if (exemplar != null) {
            exemplar.setStatus(StatusExemplar.EMPRESTADO);
        }
        return true;
    }

    private void removerMultaDoEmprestimo(int emprestimoId) {
        for (int i = 0; i < multas.size(); i++) {
            if (multas.get(i).getEmprestimoId() == emprestimoId) {
                multas.remove(i);
                return;
            }
        }
    }
}
