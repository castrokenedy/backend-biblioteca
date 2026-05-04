package br.edu.biblioteca.model;

import java.time.LocalDateTime;

public class Notificacao {
    private int id;
    private int usuarioId;
    private String mensagem;
    private LocalDateTime data;
    private boolean lida;

    public Notificacao() {}

    public Notificacao(int id, int usuarioId, String mensagem, LocalDateTime data, boolean lida) {
        this.id = id;
        this.usuarioId = usuarioId;
        this.mensagem = mensagem;
        this.data = data;
        this.lida = lida;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getUsuarioId() { return usuarioId; }
    public void setUsuarioId(int usuarioId) { this.usuarioId = usuarioId; }
    public String getMensagem() { return mensagem; }
    public void setMensagem(String mensagem) { this.mensagem = mensagem; }
    public LocalDateTime getData() { return data; }
    public void setData(LocalDateTime data) { this.data = data; }
    public boolean isLida() { return lida; }
    public void setLida(boolean lida) { this.lida = lida; }

    @Override
    public String toString() {
        return "Notificacao{" +
                "id=" + id +
                ", usuarioId=" + usuarioId +
                ", mensagem='" + mensagem + '\'' +
                ", data=" + data +
                ", lida=" + lida +
                '}';
    }
}
