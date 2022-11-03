package com.service;

import com.exception.UsuarioNoEncontradoException;
import com.model.Usuario;

public interface IUsuarioService {
    Usuario obtener(Long id) throws UsuarioNoEncontradoException;
    void crear(Usuario usuario);
//    void borrar();
    Usuario actualizar(Usuario usuario);
    boolean existePorDocumento(String documento);
    Usuario obtenerPorDocumento(String documento);
    Usuario login(String documento, String contrasenia);
}
