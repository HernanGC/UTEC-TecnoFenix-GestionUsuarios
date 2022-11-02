package com.service;

import com.model.Funcionalidad;

public class FuncionalidadBean implements FuncionalidadBeanRemote {

    @Override
    public Funcionalidad obtener(Long id) {
        return null;
    }

    @Override
    public void crear(Funcionalidad funcionalidad) {

    }

    @Override
    public Funcionalidad actualizar() {
        return null;
    }

    @Override
    public boolean existePorId(Long id) {
        return false;
    }
}
