package com.controlmas.proveedores.controller;

import com.controlmas.proveedores.models.entity.Pagos;
import com.controlmas.proveedores.service.IPagoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class PagoController {

    IPagoService pagoService;

    @GetMapping("/pagos/{id}")
    public ResponseEntity<?> listarPago (@PathVariable Integer id){
        Optional<Pagos> pago = pagoService.findById(id);
        try {
            if (pago.isPresent()) {
                return new ResponseEntity<>(pago, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("No se encontro pago", HttpStatus.NOT_FOUND);
            }
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



}
