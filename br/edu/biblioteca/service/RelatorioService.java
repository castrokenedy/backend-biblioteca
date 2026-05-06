package br.edu.biblioteca.service;

import br.edu.biblioteca.model.Emprestimo;
import br.edu.biblioteca.model.Livro;
import br.edu.biblioteca.model.Usuario;
import br.edu.biblioteca.structures.MatrizInt;
import br.edu.biblioteca.structures.Ordenador;
import br.edu.biblioteca.structures.Vetor;

import java.time.LocalDate;

public class RelatorioService {
    private final CatalogoService catalogoService;
    private final UsuarioService usuarioService;
    private final EmprestimoService emprestimoService;

    public RelatorioService(CatalogoService catalogoService, UsuarioService usuarioService, EmprestimoService emprestimoService) {
        this.catalogoService = catalogoService;
        this.usuarioService = usuarioService;
        this.emprestimoService = emprestimoService;
    }

    public Vetor<Livro> topMaisEmprestados() {
        Vetor<Livro> livros = catalogoService.listar();
        Vetor<Livro> copia = new Vetor<>();
        for (int i = 0; i < livros.size(); i++) {
            copia.add(livros.get(i));
        }
        for (int i = 0; i < copia.size() - 1; i++) {
            for (int j = 0; j < copia.size() - 1 - i; j++) {
                if (copia.get(j).getTotalEmprestimos() < copia.get(j + 1).getTotalEmprestimos()) {
                    Livro temp = copia.get(j);
                    copia.set(j, copia.get(j + 1));
                    copia.set(j + 1, temp);
                }
            }
        }
        return copia;
    }

    public Vetor<Emprestimo> emAtraso() {
        Vetor<Emprestimo> atrasados = new Vetor<>();
        for (int i = 0; i < emprestimoService.listar().size(); i++) {
            Emprestimo e = emprestimoService.listar().get(i);
            if (e.getDataDevolucao() == null && LocalDate.now().isAfter(e.getDataPrevista())) {
                atrasados.add(e);
            }
        }
        return atrasados;
    }

    public Vetor<UsuarioAtraso> usuariosComMaisAtrasos() {
        Vetor<UsuarioAtraso> resultado = new Vetor<>();
        for (int i = 0; i < usuarioService.listar().size(); i++) {
            Usuario usuario = usuarioService.listar().get(i);
            int qtd = 0;
            for (int j = 0; j < emprestimoService.listar().size(); j++) {
                Emprestimo emp = emprestimoService.listar().get(j);
                if (emp.getUsuarioId() == usuario.getId() && emp.getDataDevolucao() != null && emp.getDataDevolucao().isAfter(emp.getDataPrevista())) {
                    qtd++;
                }
            }
            resultado.add(new UsuarioAtraso(usuario, qtd));
        }
        for (int i = 0; i < resultado.size() - 1; i++) {
            for (int j = 0; j < resultado.size() - 1 - i; j++) {
                if (resultado.get(j).quantidadeAtrasos < resultado.get(j + 1).quantidadeAtrasos) {
                    UsuarioAtraso temp = resultado.get(j);
                    resultado.set(j, resultado.get(j + 1));
                    resultado.set(j + 1, temp);
                }
            }
        }
        return resultado;
    }

    public MatrizInt estatisticasMensais() {
        MatrizInt matriz = new MatrizInt(12, 1);
        for (int i = 0; i < emprestimoService.listar().size(); i++) {
            int mes = emprestimoService.listar().get(i).getDataEmprestimo().getMonthValue() - 1;
            matriz.incrementar(mes, 0);
        }
        return matriz;
    }

    public static class UsuarioAtraso {
        private final Usuario usuario;
        private final int quantidadeAtrasos;

        public UsuarioAtraso(Usuario usuario, int quantidadeAtrasos) {
            this.usuario = usuario;
            this.quantidadeAtrasos = quantidadeAtrasos;
        }

        public Usuario getUsuario() { return usuario; }
        public int getQuantidadeAtrasos() { return quantidadeAtrasos; }

        @Override
        public String toString() {
            return usuario.getNome() + " - atrasos: " + quantidadeAtrasos;
        }
    }
}
