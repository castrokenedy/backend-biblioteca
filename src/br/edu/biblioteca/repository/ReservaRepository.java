package br.edu.biblioteca.repository;

import br.edu.biblioteca.model.Reserva;
import br.edu.biblioteca.model.StatusReserva;
import br.edu.biblioteca.structures.Vetor;

import java.time.LocalDate;

/** Formato: id;usuarioId;isbnLivro;dataReserva;status */
public class ReservaRepository {
    private static final String CAMINHO = "data/reservas.csv";

    public void salvar(Vetor<Reserva> reservas) {
        Vetor<String> linhas = new Vetor<>();
        for (int i = 0; i < reservas.size(); i++) {
            Reserva r = reservas.get(i);
            linhas.add(r.getId() + ";" + r.getUsuarioId() + ";" + r.getIsbnLivro() + ";" +
                    r.getDataReserva() + ";" + r.getStatus());
        }
        FileStorage.salvar(CAMINHO, linhas);
    }

    public Vetor<Reserva> carregar() {
        Vetor<Reserva> reservas = new Vetor<>();
        Vetor<String> linhas = FileStorage.carregar(CAMINHO);
        for (int i = 0; i < linhas.size(); i++) {
            String[] c = linhas.get(i).split(";", -1);
            reservas.add(new Reserva(
                    Integer.parseInt(c[0]),
                    Integer.parseInt(c[1]),
                    c[2],
                    LocalDate.parse(c[3]),
                    StatusReserva.valueOf(c[4])
            ));
        }
        return reservas;
    }
}
