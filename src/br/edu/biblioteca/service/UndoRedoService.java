package br.edu.biblioteca.service;

import br.edu.biblioteca.structures.MinhaPilha;

public class UndoRedoService {
    private final MinhaPilha<String> acoes = new MinhaPilha<>();
    private final MinhaPilha<String> acoesDesfeitas = new MinhaPilha<>();

    public void registrarAcao(String acao) {
        acoes.push(acao);
        while (!acoesDesfeitas.isEmpty()) {
            acoesDesfeitas.pop();
        }
    }

    public String desfazer() {
        String ultima = acoes.pop();
        if (ultima != null) {
            acoesDesfeitas.push(ultima);
        }
        return ultima;
    }

    public String refazer() {
        String acao = acoesDesfeitas.pop();
        if (acao != null) {
            acoes.push(acao);
        }
        return acao;
    }
}
