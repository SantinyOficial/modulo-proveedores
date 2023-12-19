package com.controlmas.proveedores.service;

import com.controlmas.proveedores.models.entity.Pagos;

import java.util.List;
import java.util.Optional;


public interface IPagoService {

    Pagos save(Pagos pago);

    Optional<Pagos> findById(Integer id);

    List<Pagos> findAll();
}
