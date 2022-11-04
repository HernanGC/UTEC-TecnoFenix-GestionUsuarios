package com.service;

import com.exception.RolNoEncontradoException;
import com.exception.UsuarioExistenteException;
import com.model.Funcionalidad;
import com.model.Rol;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.HashSet;
import java.util.Set;

@Stateless
public class RolBean implements RolBeanRemote {

    private EntityManager entityManager;
    private FuncionalidadBean funcionalidadBean;

    public RolBean() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("GestionUsuarios");
        entityManager = factory.createEntityManager();
        funcionalidadBean = new FuncionalidadBean();
    }

    @Override
    public Rol obtener(Long id) throws RolNoEncontradoException {
        Rol rol = entityManager.find(Rol.class, id);

        if (rol == null) {
            throw new RolNoEncontradoException("El rol no existe");
        }

        return rol;
    }

    @Override
    public void crear(Rol rol) {
        entityManager.persist(rol);
        entityManager.flush();
    }

    @Override
    public Rol actualizar(Rol rol) {
        rol = entityManager.merge(rol);
        entityManager.flush();

        return rol;
    }

    @Override
    public boolean existePorId(Long id) {
        Rol rol = entityManager.find(Rol.class, id);

        return rol != null;
    }

    @Override
    public void test() {
        System.out.println("METODO TEST EJECUTADO");
    }
}
