package com.service;

import java.util.Set;

import javax.persistence.NoResultException;

import com.exception.UsuarioNoEncontradoException;
import com.model.Funcionalidad;
import com.model.Usuario;

public interface IUsuarioService {
    Usuario obtener(Long id) throws Exception;
    void crear(Usuario usuario) throws Exception;
    void borrar(String documento) throws UsuarioNoEncontradoException, Exception;
    Usuario actualizar(Usuario usuario);
    boolean existePorDocumento(String documento) throws Exception;
    Usuario obtenerPorDocumento(String documento);
    Usuario login(String documento, String contrasenia) throws UsuarioNoEncontradoException, Exception;
    Set<Funcionalidad> obtenerFuncionalidadesUsuarioPorDocumento(String documento);
}
