package br.edu.biblioteca.app;

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
    }
}
