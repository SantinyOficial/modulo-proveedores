package com.controlmas.proveedores.service.impl;

import com.controlmas.proveedores.models.entity.Facturas;
import com.controlmas.proveedores.models.entity.Pagos;
import com.controlmas.proveedores.repository.IFacturaRepository;
import com.controlmas.proveedores.repository.IPagoRepository;
import com.controlmas.proveedores.service.IFacturaService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FacturaImpl implements IFacturaService {

    @Autowired
    IFacturaRepository facturaRepository;

    @Autowired
    IPagoRepository pagoRepository;

    @Override
    public Optional<Facturas> finById(Integer id) {
        return Optional.ofNullable(facturaRepository.findById(id).orElse(null));
    }

    @Override
    public Facturas save(Facturas factura) {
        if (factura.getIdFactura() == null) {
            factura.setEstado((short) 1);
        }
        Double saldoFactura = factura.getValorFactura();
        factura.setSaldoFactura(saldoFactura) ;
        factura.setValorPago(0.00);
        return facturaRepository.save(factura);

    }


    @Override
    public List<Facturas> findAll() {
        return facturaRepository.findAll();
    }

    @Override
    public void agregarPago(Integer idFactura, Integer idPago) {
        Facturas factura = facturaRepository.findById(idFactura).orElseThrow(
                () -> new EntityNotFoundException("No se encontró la factura")
        );
        Pagos pago = pagoRepository.findById(idPago).orElseThrow(
                () -> new EntityNotFoundException("No se encontro Pago")
        );

        if (pago.isAplicado())
            throw new IllegalStateException("el pago ya ha sido aplicado anteriormente");


        double nuevoValor = factura.getValorPago() + pago.getValorPago();
        factura.setValorPago(nuevoValor);

        double saldoParcial = factura.getSaldoFactura() - pago.getValorPago();
        factura.setSaldoFactura(saldoParcial);

        pago.setAplicado(true);
        actualizarEstado(factura);

        facturaRepository.save(factura);
    }

    public static void actualizarEstado(Facturas factura){
        if (factura.getSaldoFactura() == 0) {
            factura.setEstado((short) 3);
            factura.setEstadoStr("pagada");
        } else if (factura.getSaldoFactura() > 0) {
            factura.setEstado((short) 2);
            factura.setEstadoStr("deuda parcial");
        } else {
            factura.setEstado((short) 1);
            factura.setEstadoStr("no pagada");
        }
    }

}