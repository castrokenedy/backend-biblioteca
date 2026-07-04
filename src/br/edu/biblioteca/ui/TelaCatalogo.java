package br.edu.biblioteca.ui;

import br.edu.biblioteca.action.AcaoCadastrarLivro;
import br.edu.biblioteca.action.AcaoRemoverLivro;
import br.edu.biblioteca.model.Autor;
import br.edu.biblioteca.model.Categoria;
import br.edu.biblioteca.model.Exemplar;
import br.edu.biblioteca.model.Livro;
import br.edu.biblioteca.model.StatusExemplar;
import br.edu.biblioteca.service.CatalogoService;
import br.edu.biblioteca.service.UndoRedoService;
import br.edu.biblioteca.structures.Vetor;

import java.util.Scanner;

public class TelaCatalogo {
    private final Scanner scanner;
    private final CatalogoService catalogoService;
    private final UndoRedoService undoRedoService;

    public TelaCatalogo(Scanner scanner, CatalogoService catalogoService, UndoRedoService undoRedoService) {
        this.scanner = scanner;
        this.catalogoService = catalogoService;
        this.undoRedoService = undoRedoService;
    }

    public void abrir() {
        boolean continuar = true;
        while (continuar) {
            System.out.println("\n--- CATÁLOGO ---");
            System.out.println("1. Cadastrar livro (com exemplar inicial)");
            System.out.println("2. Cadastrar exemplar adicional");
            System.out.println("3. Remover livro");
            System.out.println("4. Buscar livro por ISBN");
            System.out.println("5. Listar livros (ordenado por título)");
            System.out.println("6. Listar livros (ordenado por ano)");
            System.out.println("0. Voltar");
            System.out.print("Escolha: ");
            String opcao = scanner.nextLine().trim();

            switch (opcao) {
                case "1": cadastrarLivro(); break;
                case "2": cadastrarExemplar(); break;
                case "3": removerLivro(); break;
                case "4": buscarLivro(); break;
                case "5": listar(catalogoService.listarOrdenadoPorTitulo()); break;
                case "6": listarPorAno(); break;
                case "0": continuar = false; break;
                default: System.out.println("Opção inválida.");
            }
        }
    }

    private void cadastrarLivro() {
        System.out.print("ISBN: ");
        String isbn = scanner.nextLine().trim();
        System.out.print("Título: ");
        String titulo = scanner.nextLine().trim();
        System.out.print("Ano: ");
        int ano = lerInteiro();
        System.out.print("Nome do autor: ");
        String autorNome = scanner.nextLine().trim();
        System.out.print("Nome da categoria: ");
        String categoriaNome = scanner.nextLine().trim();

        Livro livro = new Livro(isbn, titulo, ano);
        livro.getAutores().add(new Autor(1, autorNome));
        livro.getCategorias().add(new Categoria(1, categoriaNome));

        int idExemplar = catalogoService.proximoIdExemplar();
        Exemplar exemplar = new Exemplar(idExemplar, isbn, StatusExemplar.DISPONIVEL);

        AcaoCadastrarLivro acao = new AcaoCadastrarLivro(catalogoService, livro, exemplar);
        acao.executar();
        undoRedoService.registrarAcao(acao);

        System.out.println("Livro cadastrado com exemplar nº " + idExemplar + ".");
    }

    private void cadastrarExemplar() {
        System.out.print("ISBN do livro: ");
        String isbn = scanner.nextLine().trim();
        if (catalogoService.buscar(isbn) == null) {
            System.out.println("Livro não encontrado.");
            return;
        }
        int idExemplar = catalogoService.proximoIdExemplar();
        catalogoService.cadastrarExemplar(new Exemplar(idExemplar, isbn, StatusExemplar.DISPONIVEL));
        System.out.println("Exemplar nº " + idExemplar + " cadastrado para o ISBN " + isbn + ".");
    }

    private void removerLivro() {
        System.out.print("ISBN do livro a remover: ");
        String isbn = scanner.nextLine().trim();
        if (catalogoService.buscar(isbn) == null) {
            System.out.println("Livro não encontrado.");
            return;
        }
        AcaoRemoverLivro acao = new AcaoRemoverLivro(catalogoService, isbn);
        acao.executar();
        undoRedoService.registrarAcao(acao);
        System.out.println("Livro removido.");
    }

    private void buscarLivro() {
        System.out.print("ISBN: ");
        String isbn = scanner.nextLine().trim();
        Livro livro = catalogoService.buscar(isbn);
        System.out.println(livro != null ? livro : "Livro não encontrado.");
    }

    private void listar(Vetor<Livro> livros) {
        if (livros.isEmpty()) {
            System.out.println("Nenhum livro cadastrado.");
            return;
        }
        for (int i = 0; i < livros.size(); i++) {
            System.out.println(livros.get(i));
        }
    }

    private void listarPorAno() {
        Vetor<Livro> copia = new Vetor<>();
        Vetor<Livro> todos = catalogoService.listar();
        for (int i = 0; i < todos.size(); i++) {
            copia.add(todos.get(i));
        }
        br.edu.biblioteca.structures.Ordenador.ordenarLivrosPorAno(copia);
        listar(copia);
    }

    private int lerInteiro() {
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Valor inválido, considerando 0.");
            return 0;
        }
    }
}
