package br.edu.biblioteca.ui;

import br.edu.biblioteca.model.Emprestimo;
import br.edu.biblioteca.model.Exemplar;
import br.edu.biblioteca.model.Livro;
import br.edu.biblioteca.model.Reserva;
import br.edu.biblioteca.model.Usuario;
import br.edu.biblioteca.repository.EmprestimoRepository;
import br.edu.biblioteca.repository.ExemplarRepository;
import br.edu.biblioteca.repository.LivroRepository;
import br.edu.biblioteca.repository.ReservaRepository;
import br.edu.biblioteca.repository.UsuarioRepository;
import br.edu.biblioteca.service.CatalogoService;
import br.edu.biblioteca.service.EmprestimoService;
import br.edu.biblioteca.service.RelatorioService;
import br.edu.biblioteca.service.ReservaService;
import br.edu.biblioteca.service.UndoRedoService;
import br.edu.biblioteca.service.UsuarioService;
import br.edu.biblioteca.structures.Vetor;

import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * Ponto de entrada da interface de console (2ª etapa).
 * Ao iniciar, carrega os dados salvos em /data (CSV). Ao sair, salva tudo de volta.
 */
public class MenuPrincipal {
    private final Scanner scanner = new Scanner(System.in, StandardCharsets.UTF_8);

    private final CatalogoService catalogoService = new CatalogoService();
    private final UsuarioService usuarioService = new UsuarioService();
    private final EmprestimoService emprestimoService = new EmprestimoService(catalogoService, usuarioService);
    private final ReservaService reservaService = new ReservaService();
    private final RelatorioService relatorioService = new RelatorioService(catalogoService, usuarioService, emprestimoService);
    private final UndoRedoService undoRedoService = new UndoRedoService();

    private final LivroRepository livroRepository = new LivroRepository();
    private final ExemplarRepository exemplarRepository = new ExemplarRepository();
    private final UsuarioRepository usuarioRepository = new UsuarioRepository();
    private final EmprestimoRepository emprestimoRepository = new EmprestimoRepository();
    private final ReservaRepository reservaRepository = new ReservaRepository();

    public void iniciar() {
        carregarDados();
        System.out.println("Dados carregados. Bem-vindo ao Sistema de Biblioteca!");

        boolean continuar = true;
        while (continuar) {
            System.out.println("\n===== SISTEMA DE BIBLIOTECA =====");
            System.out.println("1. Catálogo");
            System.out.println("2. Usuários");
            System.out.println("3. Empréstimos");
            System.out.println("4. Reservas");
            System.out.println("5. Relatórios");
            System.out.println("0. Salvar e sair");
            System.out.print("Escolha: ");
            String opcao = scanner.nextLine().trim();

            switch (opcao) {
                case "1": new TelaCatalogo(scanner, catalogoService, undoRedoService).abrir(); break;
                case "2": new TelaUsuarios(scanner, usuarioService).abrir(); break;
                case "3": new TelaEmprestimos(scanner, emprestimoService, undoRedoService).abrir(); break;
                case "4": new TelaReservas(scanner, reservaService, undoRedoService).abrir(); break;
                case "5": new TelaRelatorios(scanner, relatorioService).abrir(); break;
                case "0":
                    salvarDados();
                    System.out.println("Dados salvos. Até logo!");
                    continuar = false;
                    break;
                default: System.out.println("Opção inválida.");
            }
        }
        scanner.close();
    }

    private void carregarDados() {
        Vetor<Livro> livros = livroRepository.carregar();
        for (int i = 0; i < livros.size(); i++) {
            catalogoService.cadastrarLivro(livros.get(i));
        }

        Vetor<Exemplar> exemplares = exemplarRepository.carregar();
        for (int i = 0; i < exemplares.size(); i++) {
            catalogoService.cadastrarExemplar(exemplares.get(i));
        }

        Vetor<Usuario> usuarios = usuarioRepository.carregar();
        for (int i = 0; i < usuarios.size(); i++) {
            usuarioService.cadastrarUsuario(usuarios.get(i));
        }

        Vetor<Emprestimo> emprestimos = emprestimoRepository.carregar();
        for (int i = 0; i < emprestimos.size(); i++) {
            emprestimoService.carregarEmprestimo(emprestimos.get(i));
        }

        Vetor<Reserva> reservas = reservaRepository.carregar();
        for (int i = 0; i < reservas.size(); i++) {
            reservaService.carregarReserva(reservas.get(i));
        }
    }

    private void salvarDados() {
        livroRepository.salvar(catalogoService.listar());
        exemplarRepository.salvar(catalogoService.getExemplares());
        usuarioRepository.salvar(usuarioService.listar());
        emprestimoRepository.salvar(emprestimoService.listar());
        reservaRepository.salvar(reservaService.listar());
    }
}
