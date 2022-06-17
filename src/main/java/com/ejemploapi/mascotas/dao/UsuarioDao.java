package com.ejemploapi.mascotas.dao;

import com.ejemploapi.mascotas.models.Usuario;

import java.util.List;

public interface UsuarioDao {
    List<Usuario> gertUsuarios();

    void eliminar(Long id);

    void registrar(Usuario usuario);

    Usuario obtenerUsuarioPorCredenciales(Usuario usuario);
}
