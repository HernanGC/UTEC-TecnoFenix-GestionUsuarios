package com.service;

import com.exception.UsuarioExistenteException;
import com.model.Rol;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@Stateless
public class RolBean implements RolBeanRemote {

    private EntityManager entityManager;

    public RolBean() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("GestionUsuarios");
        entityManager = factory.createEntityManager();
    }

    @Override
    public Rol obtener(Long id) {
        Rol rol = entityManager.find(Rol.class, id);

        if (rol == null) {
            throw new UsuarioExistenteException("El rol no existe");
        }

        return rol;
    }

    @Override
    public void crear(Rol rol) {
        entityManager.persist(rol);
        entityManager.flush();
    }

    @Override
    public Rol actualizar() {
        return null;
    }

    @Override
    public boolean existePorId(Long id) {
        Rol rol = entityManager.find(Rol.class, id);

        return rol != null;
    }
}
