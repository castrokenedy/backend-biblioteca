package br.edu.biblioteca.ui;

import br.edu.biblioteca.action.AcaoDevolver;
import br.edu.biblioteca.action.AcaoEmpresta;
import br.edu.biblioteca.model.Emprestimo;
import br.edu.biblioteca.service.EmprestimoService;
import br.edu.biblioteca.service.UndoRedoService;
import br.edu.biblioteca.structures.Vetor;

import java.util.Scanner;

public class TelaEmprestimos {
    private final Scanner scanner;
    private final EmprestimoService emprestimoService;
    private final UndoRedoService undoRedoService;

    public TelaEmprestimos(Scanner scanner, EmprestimoService emprestimoService, UndoRedoService undoRedoService) {
        this.scanner = scanner;
        this.emprestimoService = emprestimoService;
        this.undoRedoService = undoRedoService;
    }

    public void abrir() {
        boolean continuar = true;
        while (continuar) {
            System.out.println("\n--- EMPRÉSTIMOS ---");
            System.out.println("1. Emprestar exemplar");
            System.out.println("2. Devolver exemplar");
            System.out.println("3. Renovar empréstimo");
            System.out.println("4. Calcular multa");
            System.out.println("5. Listar empréstimos");
            System.out.println("6. Desfazer última ação (Undo)");
            System.out.println("7. Refazer última ação desfeita (Redo)");
            System.out.println("0. Voltar");
            System.out.print("Escolha: ");
            String opcao = scanner.nextLine().trim();

            switch (opcao) {
                case "1": emprestar(); break;
                case "2": devolver(); break;
                case "3": renovar(); break;
                case "4": calcularMulta(); break;
                case "5": listar(); break;
                case "6": desfazer(); break;
                case "7": refazer(); break;
                case "0": continuar = false; break;
                default: System.out.println("Opção inválida.");
            }
        }
    }

    private void emprestar() {
        System.out.print("ID do usuário: ");
        int usuarioId = lerInteiro();
        System.out.print("ID do exemplar: ");
        int exemplarId = lerInteiro();

        AcaoEmpresta acao = new AcaoEmpresta(emprestimoService, usuarioId, exemplarId);
        acao.executar();

        if (acao.foiRealizadoComSucesso()) {
            undoRedoService.registrarAcao(acao);
            System.out.println("Empréstimo realizado: " + acao.getEmprestimoRealizado());
        } else {
            System.out.println("Não foi possível realizar o empréstimo (usuário bloqueado, exemplar indisponível ou inexistente).");
        }
    }

    private void devolver() {
        System.out.print("ID do empréstimo: ");
        int emprestimoId = lerInteiro();

        AcaoDevolver acao = new AcaoDevolver(emprestimoService, emprestimoId);
        acao.executar();

        if (acao.foiRealizadoComSucesso()) {
            undoRedoService.registrarAcao(acao);
            double multa = emprestimoService.calcularMulta(emprestimoId);
            System.out.println("Devolução registrada." + (multa > 0 ? " Multa gerada: R$ " + multa : ""));
        } else {
            System.out.println("Não foi possível devolver (empréstimo inexistente ou já devolvido).");
        }
    }

    private void renovar() {
        System.out.print("ID do empréstimo: ");
        int id = lerInteiro();
        System.out.println(emprestimoService.renovar(id) ? "Empréstimo renovado por mais 7 dias." : "Não foi possível renovar.");
    }

    private void calcularMulta() {
        System.out.print("ID do empréstimo: ");
        int id = lerInteiro();
        System.out.println("Multa: R$ " + emprestimoService.calcularMulta(id));
    }

    private void listar() {
        Vetor<Emprestimo> emprestimos = emprestimoService.listar();
        if (emprestimos.isEmpty()) {
            System.out.println("Nenhum empréstimo registrado.");
            return;
        }
        for (int i = 0; i < emprestimos.size(); i++) {
            System.out.println(emprestimos.get(i));
        }
    }

    private void desfazer() {
        String descricao = undoRedoService.desfazer();
        System.out.println(descricao != null ? "Desfeito: " + descricao : "Não há ações para desfazer.");
    }

    private void refazer() {
        String descricao = undoRedoService.refazer();
        System.out.println(descricao != null ? "Refeito: " + descricao : "Não há ações para refazer.");
    }

    private int lerInteiro() {
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Valor inválido, considerando 0.");
            return 0;
        }
    }
}
