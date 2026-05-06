package br.edu.biblioteca.model;

public class Multa {
    private int id;
    private int emprestimoId;
    private double valor;
    private int diasAtraso;
    private boolean quitada;

    public Multa() {}

    public Multa(int id, int emprestimoId, double valor, int diasAtraso, boolean quitada) {
        this.id = id;
        this.emprestimoId = emprestimoId;
        this.valor = valor;
        this.diasAtraso = diasAtraso;
        this.quitada = quitada;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getEmprestimoId() { return emprestimoId; }
    public void setEmprestimoId(int emprestimoId) { this.emprestimoId = emprestimoId; }
    public double getValor() { return valor; }
    public void setValor(double valor) { this.valor = valor; }
    public int getDiasAtraso() { return diasAtraso; }
    public void setDiasAtraso(int diasAtraso) { this.diasAtraso = diasAtraso; }
    public boolean isQuitada() { return quitada; }
    public void setQuitada(boolean quitada) { this.quitada = quitada; }

    @Override
    public String toString() {
        return "Multa{" +
                "id=" + id +
                ", emprestimoId=" + emprestimoId +
                ", valor=" + valor +
                ", diasAtraso=" + diasAtraso +
                ", quitada=" + quitada +
                '}';
    }
}
