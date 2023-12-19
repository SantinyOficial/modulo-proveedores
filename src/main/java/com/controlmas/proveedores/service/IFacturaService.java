package com.controlmas.proveedores.service;

import com.controlmas.proveedores.models.entity.Facturas;
import com.controlmas.proveedores.models.entity.Pagos;

import java.util.List;
import java.util.Optional;

public interface IFacturaService {

    Optional<Facturas> finById(Integer id);

    Facturas save(Facturas facturas);

    List<Facturas> findAll();

    void restarPagos(Integer idFactura, Integer idPago);


}
