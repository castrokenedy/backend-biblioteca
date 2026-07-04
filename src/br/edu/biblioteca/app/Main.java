package br.edu.biblioteca.app;

<<<<<<< HEAD
import br.edu.biblioteca.ui.MenuPrincipal;

import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

public class Main {
    public static void main(String[] args) {
        // Força saída em UTF-8 independentemente do locale do sistema operacional
        // (evita acentos quebrados no console do Windows, Linux ou macOS).
        System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8));
        System.setErr(new PrintStream(System.err, true, StandardCharsets.UTF_8));

        new MenuPrincipal().iniciar();
=======
import br.edu.biblioteca.model.*;
import br.edu.biblioteca.service.*;

public class Main {
    public static void main(String[] args) {
        CatalogoService catalogoService = new CatalogoService();
        UsuarioService usuarioService = new UsuarioService();
        EmprestimoService emprestimoService = new EmprestimoService(catalogoService, usuarioService);
        ReservaService reservaService = new ReservaService();
        RelatorioService relatorioService = new RelatorioService(catalogoService, usuarioService, emprestimoService);
        UndoRedoService undoRedoService = new UndoRedoService();

        Livro livro = new Livro("9788575228289", "Clean Code", 2008);
        livro.getAutores().add(new Autor(1, "Robert C. Martin"));
        livro.getCategorias().add(new Categoria(1, "Programação"));
        livro.getPalavrasChave().add("java");
        livro.getPalavrasChave().add("boas práticas");
        catalogoService.cadastrarLivro(livro);
        catalogoService.cadastrarExemplar(new Exemplar(1, livro.getIsbn(), StatusExemplar.DISPONIVEL));

        Usuario usuario = new Usuario(1, "Kenedy", TipoUsuario.ALUNO, "kenedy@email.com");
        usuarioService.cadastrarUsuario(usuario);

        Emprestimo emprestimo = emprestimoService.emprestarExemplar(1, 1);
        undoRedoService.registrarAcao("Empréstimo realizado: " + emprestimo);

        System.out.println("=== LIVROS ===");
        for (int i = 0; i < catalogoService.listar().size(); i++) {
            System.out.println(catalogoService.listar().get(i));
        }

        System.out.println("\n=== USUÁRIOS ===");
        for (int i = 0; i < usuarioService.listar().size(); i++) {
            System.out.println(usuarioService.listar().get(i));
        }

        System.out.println("\n=== EMPRÉSTIMOS ===");
        for (int i = 0; i < emprestimoService.listar().size(); i++) {
            System.out.println(emprestimoService.listar().get(i));
        }

        Reserva reserva = reservaService.reservarLivro(1, livro.getIsbn());
        System.out.println("\nReserva criada: " + reserva);
        System.out.println("Próxima reserva atendida: " + reservaService.atenderProximaReserva(livro.getIsbn()));

        System.out.println("\nTop mais emprestados:");
        for (int i = 0; i < relatorioService.topMaisEmprestados().size(); i++) {
            System.out.println(relatorioService.topMaisEmprestados().get(i));
        }

        System.out.println("\nDesfazer última ação: " + undoRedoService.desfazer());
>>>>>>> 24cefdf593e0554ca034471d6c0481e0de4dbfcb
    }
}
