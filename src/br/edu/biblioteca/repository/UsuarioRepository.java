package br.edu.biblioteca.repository;

import br.edu.biblioteca.model.TipoUsuario;
import br.edu.biblioteca.model.Usuario;
import br.edu.biblioteca.structures.Vetor;

/** Formato: id;nome;tipo;email;bloqueado */
public class UsuarioRepository {
    private static final String CAMINHO = "data/usuarios.csv";

    public void salvar(Vetor<Usuario> usuarios) {
        Vetor<String> linhas = new Vetor<>();
        for (int i = 0; i < usuarios.size(); i++) {
            Usuario u = usuarios.get(i);
            linhas.add(u.getId() + ";" + u.getNome() + ";" + u.getTipo() + ";" + u.getEmail() + ";" + u.isBloqueado());
        }
        FileStorage.salvar(CAMINHO, linhas);
    }

    public Vetor<Usuario> carregar() {
        Vetor<Usuario> usuarios = new Vetor<>();
        Vetor<String> linhas = FileStorage.carregar(CAMINHO);
        for (int i = 0; i < linhas.size(); i++) {
            String[] c = linhas.get(i).split(";", -1);
            Usuario usuario = new Usuario(Integer.parseInt(c[0]), c[1], TipoUsuario.valueOf(c[2]), c[3]);
            usuario.setBloqueado(Boolean.parseBoolean(c[4]));
            usuarios.add(usuario);
        }
        return usuarios;
    }
}
