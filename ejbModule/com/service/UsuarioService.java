package com.service;

import com.exception.UsuarioExistenteException;
import com.exception.UsuarioNoEncontradoException;
import com.model.Funcionalidad;
import com.model.Rol;
import com.model.Usuario;

import java.util.Set;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.*;

@Stateless
@Remote(IUsuarioService.class)
public class UsuarioService implements IUsuarioService {
//    @PersistenceContext
    private EntityManager entityManager;
    private RolBean rolBean;

    public UsuarioService() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("GestionUsuarios");
        entityManager = factory.createEntityManager();
        rolBean = new RolBean();
    }

    @Override
    public Usuario obtener(Long id) throws Exception {
        return entityManager.find(Usuario.class, id);
    }

    @Override
    public void crear(Usuario usuario) throws Exception {
        if (existePorDocumento(usuario.getDocumento())) {
            throw new UsuarioExistenteException("Ya existe un usuario registrado con ese documento.");
        }
        
        entityManager.persist(usuario);
        entityManager.flush();
    }

    
    @Override
    public void borrar(String documento) throws UsuarioNoEncontradoException, Exception {
    	if (!existePorDocumento(documento)) {
    		throw new UsuarioNoEncontradoException("El usuario que intenta borrar no existe.");
    	}
    	
    	Query query = entityManager.createNamedQuery("Usuario.deleteByDocumento");
    	query.setParameter("documento", documento);
    	query.executeUpdate();
    }

    @Override
    public Usuario actualizar(Usuario usuario) {
        usuario = entityManager.merge(usuario);
        entityManager.flush();
        return usuario;
    }

    @Override
    public boolean existePorDocumento(String documento) throws Exception {
    	try {
    		Usuario usuario = obtenerPorDocumento(documento);
    		return usuario != null;
		} catch (NoResultException ex) {
			return false;
		}
    }

    @Override
    public Usuario obtenerPorDocumento(String documento) throws NoResultException {
        TypedQuery<Usuario> query = entityManager.createNamedQuery("Usuario.findByDocumento", Usuario.class);
        Usuario usuario = query.setParameter("documento", documento).getSingleResult();
        
        return usuario;
    }

    @Override
    public Usuario login(String documento, String contrasenia) throws UsuarioNoEncontradoException, Exception {
    	if (!existePorDocumento(documento)) {
    		throw new UsuarioNoEncontradoException("El documento ingresado es incorrecto!");
    	}
    	
    	Usuario usuario = obtenerPorDocumento(documento);
    	
    	if (!usuario.getContrasenia().equals(contrasenia)) {
    		throw new UsuarioNoEncontradoException("La contrasenia ingresada es incorrecta!");
    	}
    	
    	return usuario;
    }

	@Override
	public Set<Funcionalidad> obtenerFuncionalidadesUsuarioPorDocumento(String documento) throws NoResultException {
		Usuario usuario = obtenerPorDocumento(documento);
		return usuario.getRol().getFuncionalidades();
	}


}
