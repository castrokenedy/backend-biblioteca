package br.edu.biblioteca.ui;

import br.edu.biblioteca.action.AcaoCancelarReserva;
import br.edu.biblioteca.action.AcaoReservar;
import br.edu.biblioteca.model.Reserva;
import br.edu.biblioteca.service.ReservaService;
import br.edu.biblioteca.service.UndoRedoService;
import br.edu.biblioteca.structures.Vetor;

import java.util.Scanner;

public class TelaReservas {
    private final Scanner scanner;
    private final ReservaService reservaService;
    private final UndoRedoService undoRedoService;

    public TelaReservas(Scanner scanner, ReservaService reservaService, UndoRedoService undoRedoService) {
        this.scanner = scanner;
        this.reservaService = reservaService;
        this.undoRedoService = undoRedoService;
    }

    public void abrir() {
        boolean continuar = true;
        while (continuar) {
            System.out.println("\n--- RESERVAS ---");
            System.out.println("1. Reservar livro");
            System.out.println("2. Cancelar reserva");
            System.out.println("3. Atender próxima reserva da fila");
            System.out.println("4. Listar reservas");
            System.out.println("0. Voltar");
            System.out.print("Escolha: ");
            String opcao = scanner.nextLine().trim();

            switch (opcao) {
                case "1": reservar(); break;
                case "2": cancelar(); break;
                case "3": atenderProxima(); break;
                case "4": listar(); break;
                case "0": continuar = false; break;
                default: System.out.println("Opção inválida.");
            }
        }
    }

    private void reservar() {
        System.out.print("ID do usuário: ");
        int usuarioId = lerInteiro();
        System.out.print("ISBN do livro: ");
        String isbn = scanner.nextLine().trim();

        AcaoReservar acao = new AcaoReservar(reservaService, usuarioId, isbn);
        acao.executar();
        undoRedoService.registrarAcao(acao);
        System.out.println("Reserva criada: " + acao.getReservaCriada());
    }

    private void cancelar() {
        System.out.print("ID da reserva: ");
        int id = lerInteiro();
        AcaoCancelarReserva acao = new AcaoCancelarReserva(reservaService, id);
        acao.executar();
        undoRedoService.registrarAcao(acao);
        System.out.println("Reserva cancelada (se existia).");
    }

    private void atenderProxima() {
        System.out.print("ISBN do livro: ");
        String isbn = scanner.nextLine().trim();
        Reserva atendida = reservaService.atenderProximaReserva(isbn);
        System.out.println(atendida != null ? "Reserva atendida: " + atendida : "Não há reservas pendentes para este livro.");
    }

    private void listar() {
        Vetor<Reserva> reservas = reservaService.listar();
        if (reservas.isEmpty()) {
            System.out.println("Nenhuma reserva registrada.");
            return;
        }
        for (int i = 0; i < reservas.size(); i++) {
            System.out.println(reservas.get(i));
        }
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
