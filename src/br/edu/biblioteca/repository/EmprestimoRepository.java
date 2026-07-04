package br.edu.biblioteca.repository;

import br.edu.biblioteca.model.Emprestimo;
import br.edu.biblioteca.model.StatusEmprestimo;
import br.edu.biblioteca.structures.Vetor;

import java.time.LocalDate;

/** Formato: id;usuarioId;exemplarId;dataEmprestimo;dataPrevista;dataDevolucao;status */
public class EmprestimoRepository {
    private static final String CAMINHO = "data/emprestimos.csv";

    public void salvar(Vetor<Emprestimo> emprestimos) {
        Vetor<String> linhas = new Vetor<>();
        for (int i = 0; i < emprestimos.size(); i++) {
            Emprestimo e = emprestimos.get(i);
            linhas.add(e.getId() + ";" + e.getUsuarioId() + ";" + e.getExemplarId() + ";" +
                    e.getDataEmprestimo() + ";" + e.getDataPrevista() + ";" +
                    (e.getDataDevolucao() == null ? "" : e.getDataDevolucao()) + ";" +
                    e.getStatus());
        }
        FileStorage.salvar(CAMINHO, linhas);
    }

    public Vetor<Emprestimo> carregar() {
        Vetor<Emprestimo> emprestimos = new Vetor<>();
        Vetor<String> linhas = FileStorage.carregar(CAMINHO);
        for (int i = 0; i < linhas.size(); i++) {
            String[] c = linhas.get(i).split(";", -1);
            Emprestimo emprestimo = new Emprestimo(
                    Integer.parseInt(c[0]),
                    Integer.parseInt(c[1]),
                    Integer.parseInt(c[2]),
                    LocalDate.parse(c[3]),
                    LocalDate.parse(c[4]),
                    StatusEmprestimo.valueOf(c[6])
            );
            if (c.length > 5 && !c[5].isEmpty()) {
                emprestimo.setDataDevolucao(LocalDate.parse(c[5]));
            }
            emprestimos.add(emprestimo);
        }
        return emprestimos;
    }
}
