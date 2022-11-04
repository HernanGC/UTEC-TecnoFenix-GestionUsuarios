package com.service;

import com.exception.FuncionalidadNoEncontradaException;
import com.model.Funcionalidad;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@Stateless
public class FuncionalidadBean implements FuncionalidadBeanRemote {

    private EntityManager entityManager;

    public FuncionalidadBean() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("GestionUsuarios");
        entityManager = factory.createEntityManager();
    }

    @Override
    public Funcionalidad obtener(Long id) {
        Funcionalidad funcionalidad = entityManager.find(Funcionalidad.class, id);

        if (funcionalidad == null) {
            throw new FuncionalidadNoEncontradaException("La funcionalidad no existe.");
        }

        return funcionalidad;
    }

    @Override
    public void crear(Funcionalidad funcionalidad) {
        entityManager.persist(funcionalidad);
        entityManager.flush();
    }

    @Override
    public Funcionalidad actualizar(Funcionalidad funcionalidad) {
        funcionalidad = entityManager.merge(funcionalidad);
//        entityManager.flush();

        return funcionalidad;
    }

    @Override
    public boolean existePorId(Long id) {
        Funcionalidad funcionalidad = entityManager.find(Funcionalidad.class, id);

        return funcionalidad != null;
    }
}
