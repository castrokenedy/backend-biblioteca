package br.edu.biblioteca.action;

import br.edu.biblioteca.model.Reserva;
import br.edu.biblioteca.service.ReservaService;

public class AcaoReservar implements Acao {
    private final ReservaService reservaService;
    private final int usuarioId;
    private final String isbn;
    private Reserva reservaCriada;

    public AcaoReservar(ReservaService reservaService, int usuarioId, String isbn) {
        this.reservaService = reservaService;
        this.usuarioId = usuarioId;
        this.isbn = isbn;
    }

    @Override
    public void executar() {
        reservaCriada = reservaService.reservarLivro(usuarioId, isbn);
    }

    @Override
    public void desfazer() {
        if (reservaCriada != null) {
            reservaService.cancelarReserva(reservaCriada.getId());
        }
    }

    @Override
    public String descricao() {
        return "Reserva do livro ISBN " + isbn + " pelo usuário " + usuarioId;
    }

    public Reserva getReservaCriada() {
        return reservaCriada;
    }
}
