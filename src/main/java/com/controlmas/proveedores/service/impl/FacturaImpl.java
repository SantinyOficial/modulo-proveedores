package com.controlmas.proveedores.service.impl;

import com.controlmas.proveedores.models.entity.Facturas;
import com.controlmas.proveedores.repository.IFacturaRepository;
import com.controlmas.proveedores.service.IFacturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FacturaImpl implements IFacturaService {

    @Autowired
    IFacturaRepository facturaRepository;

    @Override
    public Optional<Facturas> finById(Integer id) {
        return facturaRepository.findById(id);
    }

    @Override
    public Facturas save(Facturas factura) {
        return facturaRepository.save(factura);
    }

    @Override
    public List<Facturas> findAll() {
        return facturaRepository.findAll();
    }

}
