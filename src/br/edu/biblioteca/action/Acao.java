package br.edu.biblioteca.action;

/**
 * Padrão Command: toda ação do sistema (empréstimo, devolução, reserva, etc.)
 * é encapsulada em um objeto que sabe se executar, se desfazer e se descrever.
 * É essa interface que o UndoRedoService manipula por meio de duas pilhas.
 */
public interface Acao {
    void executar();
    void desfazer();
    String descricao();
}
