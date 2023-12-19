package com.controlmas.proveedores.service;

import com.controlmas.proveedores.models.entity.Facturas;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface IFacturaService {

    Optional<Facturas> finById(Integer id);

    Facturas save(Facturas facturas);

    List<Facturas> findAll();

    void agregarPago(Integer idFactura, Integer idPago);

}
