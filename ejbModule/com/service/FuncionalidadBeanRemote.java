package com.service;

import javax.ejb.Remote;

import com.model.Funcionalidad;

@Remote
public interface FuncionalidadBeanRemote {
    Funcionalidad obtener(Long id);
    void crear(Funcionalidad funcionalidad);
    Funcionalidad actualizar(Funcionalidad funcionalidad);
    boolean existePorId(Long id);
}
