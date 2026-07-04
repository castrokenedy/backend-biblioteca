package br.edu.biblioteca.service;

import br.edu.biblioteca.model.*;
import br.edu.biblioteca.structures.MinhaFila;
import br.edu.biblioteca.structures.Vetor;

import java.time.LocalDate;

public class ReservaService {
    private final Vetor<Reserva> reservas = new Vetor<>();
    private final Vetor<FilaReservaPorLivro> filasPorLivro = new Vetor<>();
    private int sequenciaReserva = 1;

    public Reserva reservarLivro(int usuarioId, String isbn) {
        Reserva reserva = new Reserva(sequenciaReserva++, usuarioId, isbn, LocalDate.now(), StatusReserva.RESERVADO);
        reservas.add(reserva);
        obterFila(isbn).fila.enqueue(reserva);
        return reserva;
    }

    public boolean cancelarReserva(int reservaId) {
        for (int i = 0; i < reservas.size(); i++) {
            if (reservas.get(i).getId() == reservaId) {
                reservas.get(i).setStatus(StatusReserva.CANCELADO);
                return true;
            }
        }
        return false;
    }

    public Reserva atenderProximaReserva(String isbn) {
        FilaReservaPorLivro item = obterFila(isbn);
        while (!item.fila.isEmpty()) {
            Reserva reserva = item.fila.dequeue();
            if (reserva.getStatus() == StatusReserva.RESERVADO) {
                reserva.setStatus(StatusReserva.ATENDIDO);
                return reserva;
            }
        }
        return null;
    }

    public Vetor<Reserva> listar() {
        return reservas;
    }

<<<<<<< HEAD
    public Reserva buscarPorId(int reservaId) {
        for (int i = 0; i < reservas.size(); i++) {
            if (reservas.get(i).getId() == reservaId) {
                return reservas.get(i);
            }
        }
        return null;
    }

    /** Usado pela Acao AcaoCancelarReserva para desfazer o cancelamento, devolvendo o status anterior. */
    public boolean restaurarStatus(int reservaId, StatusReserva status) {
        Reserva reserva = buscarPorId(reservaId);
        if (reserva == null) {
            return false;
        }
        reserva.setStatus(status);
        return true;
    }

    /** Usado pela camada de persistência para repopular reservas (e suas filas) ao iniciar o sistema. */
    public void carregarReserva(Reserva reserva) {
        reservas.add(reserva);
        if (reserva.getStatus() == StatusReserva.RESERVADO) {
            obterFila(reserva.getIsbnLivro()).fila.enqueue(reserva);
        }
        if (reserva.getId() >= sequenciaReserva) {
            sequenciaReserva = reserva.getId() + 1;
        }
    }

=======
>>>>>>> 24cefdf593e0554ca034471d6c0481e0de4dbfcb
    private FilaReservaPorLivro obterFila(String isbn) {
        for (int i = 0; i < filasPorLivro.size(); i++) {
            if (filasPorLivro.get(i).isbn.equals(isbn)) {
                return filasPorLivro.get(i);
            }
        }
        FilaReservaPorLivro nova = new FilaReservaPorLivro(isbn);
        filasPorLivro.add(nova);
        return nova;
    }

    private static class FilaReservaPorLivro {
        String isbn;
        MinhaFila<Reserva> fila = new MinhaFila<>();

        FilaReservaPorLivro(String isbn) {
            this.isbn = isbn;
        }
    }
}
