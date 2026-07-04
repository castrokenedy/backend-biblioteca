package br.edu.biblioteca.ui;

import br.edu.biblioteca.model.TipoUsuario;
import br.edu.biblioteca.model.Usuario;
import br.edu.biblioteca.service.UsuarioService;
import br.edu.biblioteca.structures.Vetor;

import java.util.Scanner;

public class TelaUsuarios {
    private final Scanner scanner;
    private final UsuarioService usuarioService;

    public TelaUsuarios(Scanner scanner, UsuarioService usuarioService) {
        this.scanner = scanner;
        this.usuarioService = usuarioService;
    }

    public void abrir() {
        boolean continuar = true;
        while (continuar) {
            System.out.println("\n--- USUÁRIOS ---");
            System.out.println("1. Cadastrar usuário");
            System.out.println("2. Bloquear usuário");
            System.out.println("3. Desbloquear usuário");
            System.out.println("4. Listar usuários");
            System.out.println("0. Voltar");
            System.out.print("Escolha: ");
            String opcao = scanner.nextLine().trim();

            switch (opcao) {
                case "1": cadastrar(); break;
                case "2": alterarBloqueio(true); break;
                case "3": alterarBloqueio(false); break;
                case "4": listar(); break;
                case "0": continuar = false; break;
                default: System.out.println("Opção inválida.");
            }
        }
    }

    private void cadastrar() {
        System.out.print("Nome: ");
        String nome = scanner.nextLine().trim();
        System.out.print("E-mail: ");
        String email = scanner.nextLine().trim();
        System.out.print("Tipo (ALUNO/PROFESSOR/SERVIDOR): ");
        TipoUsuario tipo;
        try {
            tipo = TipoUsuario.valueOf(scanner.nextLine().trim().toUpperCase());
        } catch (IllegalArgumentException e) {
            System.out.println("Tipo inválido, definido como ALUNO.");
            tipo = TipoUsuario.ALUNO;
        }
        int id = usuarioService.proximoId();
        usuarioService.cadastrarUsuario(new Usuario(id, nome, tipo, email));
        System.out.println("Usuário cadastrado com id " + id + ".");
    }

    private void alterarBloqueio(boolean bloquear) {
        System.out.print("ID do usuário: ");
        int id = lerInteiro();
        boolean sucesso = bloquear ? usuarioService.bloquear(id) : usuarioService.desbloquear(id);
        System.out.println(sucesso ? "Operação realizada." : "Usuário não encontrado.");
    }

    private void listar() {
        Vetor<Usuario> usuarios = usuarioService.listar();
        if (usuarios.isEmpty()) {
            System.out.println("Nenhum usuário cadastrado.");
            return;
        }
        for (int i = 0; i < usuarios.size(); i++) {
            System.out.println(usuarios.get(i));
        }
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
