package com.service;

import com.model.Rol;

import javax.ejb.Remote;

@Remote
public interface RolBeanRemote {
    Rol obtener(Long id);
    void crear(Rol rol);
    Rol actualizar(Rol rol);
    boolean existePorId(Long id);
    void test();
}
