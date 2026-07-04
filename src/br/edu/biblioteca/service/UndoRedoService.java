package br.edu.biblioteca.service;

import br.edu.biblioteca.action.Acao;
import br.edu.biblioteca.structures.MinhaPilha;

/**
 * Serviço de Undo/Redo baseado no padrão Command (interface Acao).
 * Usa duas pilhas (MinhaPilha) — uma característica clássica de LIFO
 * aplicada tanto para desfazer quanto para refazer ações.
 */
public class UndoRedoService {
    private final MinhaPilha<Acao> historico = new MinhaPilha<>();
    private final MinhaPilha<Acao> desfeitas = new MinhaPilha<>();

    /**
     * Registra uma ação que já foi executada (a UI chama acao.executar()
     * antes de registrar). Ao registrar uma nova ação, a pilha de "refazer"
     * é limpa, pois o histórico de redo se torna inválido.
     */
    public void registrarAcao(Acao acao) {
        historico.push(acao);
        while (!desfeitas.isEmpty()) {
            desfeitas.pop();
        }
    }

    public String desfazer() {
        Acao acao = historico.pop();
        if (acao == null) {
            return null;
        }
        acao.desfazer();
        desfeitas.push(acao);
        return acao.descricao();
    }

    public String refazer() {
        Acao acao = desfeitas.pop();
        if (acao == null) {
            return null;
        }
        acao.executar();
        historico.push(acao);
        return acao.descricao();
    }

    public boolean temAcaoParaDesfazer() {
        return !historico.isEmpty();
    }

    public boolean temAcaoParaRefazer() {
        return !desfeitas.isEmpty();
    }
}
