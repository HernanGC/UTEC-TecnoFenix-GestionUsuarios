package com.service;

import com.model.Funcionalidad;

public interface FuncionalidadBeanRemote {
    Funcionalidad obtener(Long id);
    void crear(Funcionalidad funcionalidad);
    Funcionalidad actualizar();
    boolean existePorId(Long id);
}
