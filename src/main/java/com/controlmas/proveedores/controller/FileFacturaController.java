package com.controlmas.proveedores.controller;

import com.controlmas.proveedores.service.IFileFacturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/files")
public class FileFacturaController {

    @Autowired
    IFileFacturaService fileFacturaService;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFiles(@RequestParam("files") List<MultipartFile> files) {
        try {
            fileFacturaService.save(files);
            return ResponseEntity.status(HttpStatus.OK).body("Archivos cargados correctamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocurrio un error al subir el archivo");
        }
    }
}
