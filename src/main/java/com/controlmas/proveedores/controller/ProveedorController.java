package com.controlmas.proveedores.controller;


import com.controlmas.proveedores.models.entity.Proveedores;
import com.controlmas.proveedores.service.IProveedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/controlmas/v2")
public class ProveedorController {

    @Autowired
    private IProveedorService proveedorService;


    @RequestMapping("/lista/proveedores")
    public ResponseEntity<List<Proveedores>> listarProveedores(){
        try{
            List<Proveedores> proveedores = proveedorService.findAll();
            if (!proveedores.isEmpty()){
                return new ResponseEntity<>(proveedores, HttpStatus.OK);
            }else{
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/listar/proveedor/{id}")
    public ResponseEntity<Proveedores> listarPorId(@PathVariable Integer id){
        try{
            Proveedores proveedor = proveedorService.getId(id);
            return new ResponseEntity<>(proveedor, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping
    public ResponseEntity<Proveedores> AgregarProveedor(@RequestBody Proveedores proveedor){
        Proveedores newProveedor = proveedorService.save(proveedor);
        return new ResponseEntity<>(newProveedor, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Proveedores> updateProveedor(@RequestBody Proveedores proveedor){
        Proveedores editarProveedor = proveedorService.save(proveedor);
        return new ResponseEntity<>(editarProveedor, HttpStatus.OK);
    }



}
