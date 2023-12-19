package com.controlmas.proveedores.controller;

import com.controlmas.proveedores.models.entity.Facturas;
import com.controlmas.proveedores.models.entity.Pagos;
import com.controlmas.proveedores.service.IFacturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class FacturaController {

    @Autowired
    private IFacturaService facturaService;

    @GetMapping("/facturas/{idFactura}")
    public ResponseEntity<?> listarFactura(@PathVariable Integer idFactura) {
        Optional<Facturas> factura = facturaService.finById(idFactura);

        try {
            if (factura.isPresent()) {
                return new ResponseEntity<>(factura, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("No se encontró factura", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/facturas")
    public ResponseEntity<List<Facturas>> listarFacturas() {
        try {
            List<Facturas> facturasList = facturaService.findAll();
            if (!facturasList.isEmpty()) {
                return new ResponseEntity<>(facturasList, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


    @PostMapping("/facturas")
    public ResponseEntity<Facturas> agregarFactura(@RequestBody Facturas factura) {
        Facturas newFactura = facturaService.save(factura);
        return new ResponseEntity<>(newFactura, HttpStatus.CREATED);
    }


    @PostMapping("/facturas/{idFactura}/pagos")
    public ResponseEntity<?> asociarPago(@PathVariable Integer idFactura, @RequestBody Pagos pago) {
        Optional<Facturas> factura = facturaService.finById(idFactura);
        if (factura.isPresent()) {
            Facturas facturas = factura.get();

            pago.setFactura(facturas);
            facturas.getPagos().add(pago);

            facturaService.save(facturas);

            return new ResponseEntity<>("Pago asociado con exito", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("no existe factura", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/facturas/{idFactura}/pagos/{idPago}")
    public ResponseEntity<?> agregaPago(@PathVariable Integer idFactura, @PathVariable Integer idPago) {
        try {
            facturaService.agregarPago(idFactura, idPago);
            return ResponseEntity.ok("Pago restado con éxito");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("La factura o el pago no fueron encontrados");
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("El pago ya ha sido aplicado anteriormente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno del servidor");
        }

    }
}
