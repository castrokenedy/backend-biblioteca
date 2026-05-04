package br.edu.biblioteca.model;

import java.time.LocalDate;

public class Reserva {
    private int id;
    private int usuarioId;
    private String isbnLivro;
    private LocalDate dataReserva;
    private StatusReserva status;

    public Reserva() {}

    public Reserva(int id, int usuarioId, String isbnLivro, LocalDate dataReserva, StatusReserva status) {
        this.id = id;
        this.usuarioId = usuarioId;
        this.isbnLivro = isbnLivro;
        this.dataReserva = dataReserva;
        this.status = status;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getUsuarioId() { return usuarioId; }
    public void setUsuarioId(int usuarioId) { this.usuarioId = usuarioId; }
    public String getIsbnLivro() { return isbnLivro; }
    public void setIsbnLivro(String isbnLivro) { this.isbnLivro = isbnLivro; }
    public LocalDate getDataReserva() { return dataReserva; }
    public void setDataReserva(LocalDate dataReserva) { this.dataReserva = dataReserva; }
    public StatusReserva getStatus() { return status; }
    public void setStatus(StatusReserva status) { this.status = status; }

    @Override
    public String toString() {
        return "Reserva{" +
                "id=" + id +
                ", usuarioId=" + usuarioId +
                ", isbnLivro='" + isbnLivro + '\'' +
                ", dataReserva=" + dataReserva +
                ", status=" + status +
                '}';
    }
}
