package com.service;

import com.exception.UsuarioExistenteException;
import com.exception.UsuarioNoEncontradoException;
import com.model.Usuario;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.*;

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
        if (existePorDocumento(usuario.getDocumento())) {
            throw new UsuarioExistenteException("El usuario que se intenta crear ya existe");
        }

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

    @Override
    public boolean existePorDocumento(String documento) {
        TypedQuery<Usuario> query = entityManager.createNamedQuery("findByDocumento", Usuario.class);
        Usuario usuario = query.setParameter("documento", documento).getSingleResult();

        return usuario != null;
    }


}
