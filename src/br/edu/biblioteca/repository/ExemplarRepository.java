package br.edu.biblioteca.repository;

import br.edu.biblioteca.model.Exemplar;
import br.edu.biblioteca.model.StatusExemplar;
import br.edu.biblioteca.structures.Vetor;

/** Formato: id;isbnLivro;status */
public class ExemplarRepository {
    private static final String CAMINHO = "data/exemplares.csv";

    public void salvar(Vetor<Exemplar> exemplares) {
        Vetor<String> linhas = new Vetor<>();
        for (int i = 0; i < exemplares.size(); i++) {
            Exemplar e = exemplares.get(i);
            linhas.add(e.getId() + ";" + e.getIsbnLivro() + ";" + e.getStatus());
        }
        FileStorage.salvar(CAMINHO, linhas);
    }

    public Vetor<Exemplar> carregar() {
        Vetor<Exemplar> exemplares = new Vetor<>();
        Vetor<String> linhas = FileStorage.carregar(CAMINHO);
        for (int i = 0; i < linhas.size(); i++) {
            String[] c = linhas.get(i).split(";", -1);
            exemplares.add(new Exemplar(Integer.parseInt(c[0]), c[1], StatusExemplar.valueOf(c[2])));
        }
        return exemplares;
    }
}
