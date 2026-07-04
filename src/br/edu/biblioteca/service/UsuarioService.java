package br.edu.biblioteca.service;

import br.edu.biblioteca.model.Usuario;
import br.edu.biblioteca.structures.Vetor;

public class UsuarioService {
    private final Vetor<Usuario> usuarios = new Vetor<>();
    private int sequenciaUsuario = 1;

    public void cadastrarUsuario(Usuario usuario) {
        usuarios.add(usuario);
        if (usuario.getId() >= sequenciaUsuario) {
            sequenciaUsuario = usuario.getId() + 1;
        }
    }

    public int proximoId() {
        return sequenciaUsuario;
    }

    public boolean bloquear(int usuarioId) {
        Usuario usuario = buscarPorId(usuarioId);
        if (usuario != null) {
            usuario.setBloqueado(true);
            return true;
        }
        return false;
    }

    public boolean desbloquear(int usuarioId) {
        Usuario usuario = buscarPorId(usuarioId);
        if (usuario != null) {
            usuario.setBloqueado(false);
            return true;
        }
        return false;
    }

    public Vetor<Usuario> listar() {
        return usuarios;
    }

    public Usuario buscarPorId(int id) {
        for (int i = 0; i < usuarios.size(); i++) {
            if (usuarios.get(i).getId() == id) {
                return usuarios.get(i);
            }
        }
        return null;
    }
}
