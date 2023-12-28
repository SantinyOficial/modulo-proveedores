package com.controlmas.proveedores.controller;

import com.controlmas.proveedores.models.entity.DocumentoSoporte;
import com.controlmas.proveedores.models.entity.Facturas;
import com.controlmas.proveedores.models.entity.Pagos;
import com.controlmas.proveedores.service.IDocumentoSoporteService;
import com.controlmas.proveedores.service.IPagoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class PagoController {

    @Autowired
    IPagoService pagoService;

    @Autowired
    IDocumentoSoporteService documentoSoporteService;

    @PostMapping(value = "/pagos", consumes = "multipart/form-data")
    public ResponseEntity<?> registrarPago(@RequestParam("idFactura") Integer idFactura,
                                           @RequestParam("valorPago") Double valorPago, @RequestParam("file") MultipartFile file) {
        try {
            Pagos pagos = new Pagos();
            pagos.setFactura( new Facturas(idFactura));
            pagos.setValorPago(valorPago);
            pagos.setAplicado(false);

            String direccionDocumento = documentoSoporteService.save(file);

            DocumentoSoporte documentoSoporte = new DocumentoSoporte();
            documentoSoporte.setNombreDocumento(file.getOriginalFilename());
            documentoSoporte.setDireccionDocumento(direccionDocumento);

            documentoSoporte = documentoSoporteService.saveDocumento(documentoSoporte);
            pagos.setDocumentoSoporte(documentoSoporte);

            Pagos newPagos = pagoService.save(pagos, file);
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
