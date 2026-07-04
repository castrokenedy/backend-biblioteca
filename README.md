# Sistema de Biblioteca em Java

Projeto do trabalho de Estrutura de Dados — 1ª e 2ª etapa completas.

## Alunos

- Ketleen de Souza Santos
- Kenedy Anderson Souza de Castro
- Jonas Guilhermino Nascimento
- Gabriel Oliveira Ramos

## Pacotes

- `br.edu.biblioteca.model` — entidades do domínio (Livro, Usuario, Emprestimo, Reserva, Multa, Notificacao, Exemplar, Autor, Categoria e enums de status)
- `br.edu.biblioteca.structures` — estruturas de dados implementadas do zero (Vetor, MatrizInt, MinhaPilha, MinhaFila, ArvoreBST, Grafo, Ordenador)
- `br.edu.biblioteca.service` — regras de negócio (CatalogoService, UsuarioService, EmprestimoService, ReservaService, RelatorioService, UndoRedoService)
- `br.edu.biblioteca.action` — **(2ª etapa)** implementação do padrão Command: interface `Acao` (executar/desfazer/descricao) e as ações concretas `AcaoEmpresta`, `AcaoDevolver`, `AcaoCadastrarLivro`, `AcaoRemoverLivro`, `AcaoReservar`, `AcaoCancelarReserva`
- `br.edu.biblioteca.repository` — **(2ª etapa)** persistência em arquivos CSV: `FileStorage` (utilitário genérico de leitura/gravação de linhas) e os repositórios `LivroRepository`, `UsuarioRepository`, `ExemplarRepository`, `EmprestimoRepository`, `ReservaRepository`
- `br.edu.biblioteca.ui` — **(2ª etapa)** interface de console: `MenuPrincipal` (carrega/salva dados e orquestra as telas) e as telas `TelaCatalogo`, `TelaUsuarios`, `TelaEmprestimos`, `TelaReservas`, `TelaRelatorios`
- `br.edu.biblioteca.app` — `Main`, ponto de entrada

## Estruturas implementadas

- `Vetor<T>` — array dinâmico próprio (usado como base de todas as outras estruturas)
- `MatrizInt` — matriz para estatísticas mensais
- `MinhaPilha<T>` — LIFO, base do Undo/Redo
- `MinhaFila<T>` — FIFO, base da fila de reservas por livro
- `ArvoreBST<K,V>` — índice de busca eficiente dos livros por ISBN
- `Grafo<T>` — reservado para recomendações futuras
- `Ordenador` — Bubble Sort por título e por ano

## Funcionalidades

**1ª etapa:** cadastro de livros/exemplares/usuários, empréstimo, devolução, renovação, cálculo de multa, reserva, atendimento de reserva, relatórios.

**2ª etapa:**
- Undo/Redo real via padrão Command (pilha de ações desfeitas/refeitas)
- Persistência automática dos dados em CSV (pasta `data/`, criada no primeiro uso)
- Interface de console completa e navegável por menus

## Como compilar

```
javac -d bin -encoding UTF-8 $(find src -name "*.java")
```

## Como executar

```
java -cp bin br.edu.biblioteca.app.Main
```

Windows (PowerShell/CMD):
```
javac -d bin -encoding UTF-8 (Get-ChildItem -Recurse -Filter *.java src | % { $_.FullName })
java -cp bin br.edu.biblioteca.app.Main
```
> Se aparecerem caracteres estranhos no lugar dos acentos, garanta que está usando o mesmo JDK para compilar e executar; o `Main` já força a saída em UTF-8.

Ao sair pela opção **0** do menu principal, os dados são salvos em `data/*.csv` e recarregados automaticamente na próxima execução.

## Observação

Entrega completa da 1ª e 2ª etapa, conforme o Plano de Aprendizagem da disciplina.
