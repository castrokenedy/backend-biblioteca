package br.edu.biblioteca.model;

public class Exemplar {
    private int id;
    private String isbnLivro;
    private StatusExemplar status;

    public Exemplar() {}

    public Exemplar(int id, String isbnLivro, StatusExemplar status) {
        this.id = id;
        this.isbnLivro = isbnLivro;
        this.status = status;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getIsbnLivro() { return isbnLivro; }
    public void setIsbnLivro(String isbnLivro) { this.isbnLivro = isbnLivro; }
    public StatusExemplar getStatus() { return status; }
    public void setStatus(StatusExemplar status) { this.status = status; }

    @Override
    public String toString() {
        return "Exemplar{" +
                "id=" + id +
                ", isbnLivro='" + isbnLivro + '\'' +
                ", status=" + status +
                '}';
    }
}
