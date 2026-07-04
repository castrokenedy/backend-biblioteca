package br.edu.biblioteca.action;

import br.edu.biblioteca.model.Reserva;
import br.edu.biblioteca.model.StatusReserva;
import br.edu.biblioteca.service.ReservaService;

public class AcaoCancelarReserva implements Acao {
    private final ReservaService reservaService;
    private final int reservaId;
    private StatusReserva statusAnterior;

    public AcaoCancelarReserva(ReservaService reservaService, int reservaId) {
        this.reservaService = reservaService;
        this.reservaId = reservaId;
    }

    @Override
    public void executar() {
        Reserva reserva = reservaService.buscarPorId(reservaId);
        if (reserva != null) {
            statusAnterior = reserva.getStatus();
        }
        reservaService.cancelarReserva(reservaId);
    }

    @Override
    public void desfazer() {
        if (statusAnterior != null) {
            reservaService.restaurarStatus(reservaId, statusAnterior);
        }
    }

    @Override
    public String descricao() {
        return "Cancelamento da reserva " + reservaId;
    }
}
