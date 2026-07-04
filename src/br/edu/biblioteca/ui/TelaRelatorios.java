package br.edu.biblioteca.ui;

import br.edu.biblioteca.model.Emprestimo;
import br.edu.biblioteca.model.Livro;
import br.edu.biblioteca.service.RelatorioService;
import br.edu.biblioteca.structures.MatrizInt;
import br.edu.biblioteca.structures.Vetor;

import java.time.Month;
import java.util.Scanner;

public class TelaRelatorios {
    private final Scanner scanner;
    private final RelatorioService relatorioService;

    public TelaRelatorios(Scanner scanner, RelatorioService relatorioService) {
        this.scanner = scanner;
        this.relatorioService = relatorioService;
    }

    public void abrir() {
        boolean continuar = true;
        while (continuar) {
            System.out.println("\n--- RELATÓRIOS ---");
            System.out.println("1. Top livros mais emprestados");
            System.out.println("2. Empréstimos em atraso");
            System.out.println("3. Usuários com mais atrasos");
            System.out.println("4. Estatísticas mensais de empréstimos");
            System.out.println("0. Voltar");
            System.out.print("Escolha: ");
            String opcao = scanner.nextLine().trim();

            switch (opcao) {
                case "1": topMaisEmprestados(); break;
                case "2": emAtraso(); break;
                case "3": usuariosComMaisAtrasos(); break;
                case "4": estatisticasMensais(); break;
                case "0": continuar = false; break;
                default: System.out.println("Opção inválida.");
            }
        }
    }

    private void topMaisEmprestados() {
        Vetor<Livro> livros = relatorioService.topMaisEmprestados();
        if (livros.isEmpty()) {
            System.out.println("Nenhum livro cadastrado.");
            return;
        }
        for (int i = 0; i < livros.size(); i++) {
            System.out.println((i + 1) + "º - " + livros.get(i));
        }
    }

    private void emAtraso() {
        Vetor<Emprestimo> atrasados = relatorioService.emAtraso();
        if (atrasados.isEmpty()) {
            System.out.println("Nenhum empréstimo em atraso.");
            return;
        }
        for (int i = 0; i < atrasados.size(); i++) {
            System.out.println(atrasados.get(i));
        }
    }

    private void usuariosComMaisAtrasos() {
        Vetor<RelatorioService.UsuarioAtraso> resultado = relatorioService.usuariosComMaisAtrasos();
        if (resultado.isEmpty()) {
            System.out.println("Nenhum usuário cadastrado.");
            return;
        }
        for (int i = 0; i < resultado.size(); i++) {
            System.out.println(resultado.get(i));
        }
    }

    private void estatisticasMensais() {
        MatrizInt matriz = relatorioService.estatisticasMensais();
        for (int mes = 0; mes < matriz.linhas(); mes++) {
            int total = matriz.get(mes, 0);
            if (total > 0) {
                System.out.println(Month.of(mes + 1) + ": " + total + " empréstimo(s)");
            }
        }
    }
}
