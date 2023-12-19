package com.controlmas.proveedores.controller;

import com.controlmas.proveedores.models.entity.Pagos;
import com.controlmas.proveedores.service.IPagoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class PagoController {

    @Autowired
    IPagoService pagoService;

    @PostMapping("/pagos")
    public ResponseEntity<?> registrarPago(@RequestBody Pagos pagos) {
        try {
            pagos.setAplicado(false);
            Pagos newPagos = pagoService.save(pagos);
            return new ResponseEntity<>(newPagos, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/pagos")
    public ResponseEntity<List<Pagos>> listarPagos() {
        List<Pagos> listar = pagoService.findAll();
        try {
            if (!listar.isEmpty())
                return new ResponseEntity<>(listar, HttpStatus.OK);
            else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/pagos/{id}")
    public ResponseEntity<?> listarPago(@PathVariable Integer id) {
        Optional<Pagos> pago = pagoService.findById(id);
        try {
            if (pago.isPresent()) {
                return new ResponseEntity<>(pago, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("No se encontro pago", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
