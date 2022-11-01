package com.service;

import com.exception.UsuarioNoEncontradoException;
import com.model.Usuario;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

@Stateless
@Remote(IUsuarioService.class)
public class UsuarioService implements IUsuarioService {
//    @PersistenceContext
    private EntityManager entityManager;

    public UsuarioService() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("GestionUsuarios");
        entityManager = factory.createEntityManager();
    }

    @Override
    public Usuario obtener(Long id) throws UsuarioNoEncontradoException {
        Usuario usuario = entityManager.find(Usuario.class, id);

        if (usuario == null) {
            throw new UsuarioNoEncontradoException("El usuario no se ha encontrado");
        }

        return usuario;
    }

    @Override
    public void crear(Usuario usuario) {
        entityManager.persist(usuario);
        entityManager.flush();
    }

    /*
    @Override
    public void borrar() {
        entityManager.de
    }*/

    @Override
    public Usuario actualizar(Usuario usuario) {
        usuario = entityManager.merge(usuario);
        entityManager.flush();
        return usuario;
    }
}
