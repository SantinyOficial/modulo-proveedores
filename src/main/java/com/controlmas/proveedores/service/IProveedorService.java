package com.controlmas.proveedores.service;

import com.controlmas.proveedores.models.entity.Proveedores;

import java.util.List;
public interface IProveedorService {

    List<Proveedores> findAll();

    Proveedores save(Proveedores proveedor);

    Proveedores getId (Integer id);
}
