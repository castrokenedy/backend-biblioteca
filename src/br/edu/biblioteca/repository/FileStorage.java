package br.edu.biblioteca.repository;

import br.edu.biblioteca.structures.Vetor;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Utilitário genérico de leitura/gravação de arquivos texto (CSV/TXT),
 * usado por todos os *Repository. Cada linha do arquivo vira um elemento
 * do Vetor<String>, na ordem em que foi gravada.
 */
public class FileStorage {

    private FileStorage() {
    }

    public static void salvar(String caminho, Vetor<String> linhas) {
        criarDiretorioSeNecessario(caminho);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(caminho))) {
            for (int i = 0; i < linhas.size(); i++) {
                writer.write(linhas.get(i));
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar arquivo " + caminho + ": " + e.getMessage());
        }
    }

    public static Vetor<String> carregar(String caminho) {
        Vetor<String> linhas = new Vetor<>();
        File arquivo = new File(caminho);
        if (!arquivo.exists()) {
            return linhas;
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                if (!linha.trim().isEmpty()) {
                    linhas.add(linha);
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler arquivo " + caminho + ": " + e.getMessage());
        }
        return linhas;
    }

    private static void criarDiretorioSeNecessario(String caminho) {
        File arquivo = new File(caminho);
        File diretorio = arquivo.getParentFile();
        if (diretorio != null && !diretorio.exists()) {
            diretorio.mkdirs();
        }
    }
}
