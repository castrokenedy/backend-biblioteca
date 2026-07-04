package br.edu.biblioteca.model;

import br.edu.biblioteca.structures.Vetor;

public class Livro {
    private String isbn;
    private String titulo;
    private int ano;
    private Vetor<Categoria> categorias;
    private Vetor<Autor> autores;
    private Vetor<String> palavrasChave;
    private int totalEmprestimos;

    public Livro() {
        this.categorias = new Vetor<>();
        this.autores = new Vetor<>();
        this.palavrasChave = new Vetor<>();
    }

    public Livro(String isbn, String titulo, int ano) {
        this();
        this.isbn = isbn;
        this.titulo = titulo;
        this.ano = ano;
    }

    public String getIsbn() { return isbn; }
    public void setIsbn(String isbn) { this.isbn = isbn; }
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public int getAno() { return ano; }
    public void setAno(int ano) { this.ano = ano; }
    public Vetor<Categoria> getCategorias() { return categorias; }
    public void setCategorias(Vetor<Categoria> categorias) { this.categorias = categorias; }
    public Vetor<Autor> getAutores() { return autores; }
    public void setAutores(Vetor<Autor> autores) { this.autores = autores; }
    public Vetor<String> getPalavrasChave() { return palavrasChave; }
    public void setPalavrasChave(Vetor<String> palavrasChave) { this.palavrasChave = palavrasChave; }
    public int getTotalEmprestimos() { return totalEmprestimos; }
    public void setTotalEmprestimos(int totalEmprestimos) { this.totalEmprestimos = totalEmprestimos; }
    public void incrementarEmprestimos() { this.totalEmprestimos++; }
    public void decrementarEmprestimos() { if (this.totalEmprestimos > 0) this.totalEmprestimos--; }

    @Override
    public String toString() {
        return "Livro{" +
                "isbn='" + isbn + '\'' +
                ", titulo='" + titulo + '\'' +
                ", ano=" + ano +
                ", totalEmprestimos=" + totalEmprestimos +
                '}';
    }
}
