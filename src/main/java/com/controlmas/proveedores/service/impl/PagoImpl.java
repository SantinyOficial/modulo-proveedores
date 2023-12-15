package com.controlmas.proveedores.service.impl;

import com.controlmas.proveedores.models.entity.Pagos;
import com.controlmas.proveedores.repository.IPagoRepository;
import com.controlmas.proveedores.service.IPagoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PagoImpl implements IPagoService {

    @Autowired
    IPagoRepository pagoRepository;


    @Override
    public Pagos save(Pagos pago) {
        return pagoRepository.save(pago);
    }

    @Override
    public Optional<Pagos> findById(Integer id) {
        return pagoRepository.findById(id);
    }

    @Override
    public List<Pagos> findAll() {
        return pagoRepository.findAll();
    }
}
