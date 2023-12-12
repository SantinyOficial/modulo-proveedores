package com.controlmas.proveedores.service.impl;


import com.controlmas.proveedores.models.entity.Proveedores;
import com.controlmas.proveedores.repository.IProveedorRepository;
import com.controlmas.proveedores.service.IProveedorService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ProveedorServiceImpl implements IProveedorService {

    @Autowired
    private IProveedorRepository proveedorRepository;

    @Override
    public List<Proveedores> findAll() {
        return proveedorRepository.findAll();
    }

    @Override
    public Proveedores save(Proveedores proveedores) {
       return proveedorRepository.save(proveedores);
    }

    @Override
    public Proveedores getId(Integer id) {
        return proveedorRepository.findById(id).get();
    }
}
