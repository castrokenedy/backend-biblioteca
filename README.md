# Sistema de Biblioteca em Java

Projeto base para a **primeira etapa** do trabalho de Estrutura de Dados.

## Pacotes
- `br.edu.biblioteca.model`
- `br.edu.biblioteca.structures`
- `br.edu.biblioteca.service`
- `br.edu.biblioteca.app`

## Estruturas implementadas
- `Vetor<T>`
- `MatrizInt`
- `MinhaPilha<T>`
- `MinhaFila<T>`
- `ArvoreBST<K,V>`
- `Grafo<T>`
- `Ordenador`

## Funcionalidades implementadas
- Cadastro de livros
- Cadastro de exemplares
- Cadastro de usuários
- Empréstimo de exemplar
- Devolução de exemplar
- Renovação
- Cálculo de multa
- Reserva de livros
- Atendimento da próxima reserva
- Relatórios iniciais
- Undo/Redo básico com pilha

## Como compilar
```bash
javac -d bin $(find src -name "*.java")
```

## Como executar
```bash
java -cp bin br.edu.biblioteca.app.Main
```

## Observação
Esta entrega está focada na **primeira etapa** e serve como base para evolução no próximo bimestre.
