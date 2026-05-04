package br.edu.biblioteca.model;

import java.time.LocalDate;

public class Emprestimo {
    private int id;
    private int usuarioId;
    private int exemplarId;
    private LocalDate dataEmprestimo;
    private LocalDate dataPrevista;
    private LocalDate dataDevolucao;
    private StatusEmprestimo status;

    public Emprestimo() {}

    public Emprestimo(int id, int usuarioId, int exemplarId, LocalDate dataEmprestimo, LocalDate dataPrevista, StatusEmprestimo status) {
        this.id = id;
        this.usuarioId = usuarioId;
        this.exemplarId = exemplarId;
        this.dataEmprestimo = dataEmprestimo;
        this.dataPrevista = dataPrevista;
        this.status = status;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getUsuarioId() { return usuarioId; }
    public void setUsuarioId(int usuarioId) { this.usuarioId = usuarioId; }
    public int getExemplarId() { return exemplarId; }
    public void setExemplarId(int exemplarId) { this.exemplarId = exemplarId; }
    public LocalDate getDataEmprestimo() { return dataEmprestimo; }
    public void setDataEmprestimo(LocalDate dataEmprestimo) { this.dataEmprestimo = dataEmprestimo; }
    public LocalDate getDataPrevista() { return dataPrevista; }
    public void setDataPrevista(LocalDate dataPrevista) { this.dataPrevista = dataPrevista; }
    public LocalDate getDataDevolucao() { return dataDevolucao; }
    public void setDataDevolucao(LocalDate dataDevolucao) { this.dataDevolucao = dataDevolucao; }
    public StatusEmprestimo getStatus() { return status; }
    public void setStatus(StatusEmprestimo status) { this.status = status; }

    @Override
    public String toString() {
        return "Emprestimo{" +
                "id=" + id +
                ", usuarioId=" + usuarioId +
                ", exemplarId=" + exemplarId +
                ", dataEmprestimo=" + dataEmprestimo +
                ", dataPrevista=" + dataPrevista +
                ", dataDevolucao=" + dataDevolucao +
                ", status=" + status +
                '}';
    }
}
