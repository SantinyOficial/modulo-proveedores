package com.controlmas.proveedores.controller;

import com.controlmas.proveedores.models.entity.DocumentoSoporte;
import com.controlmas.proveedores.service.IDocumentoSoporteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api")
public class DocumentoSoporteController {


    @Autowired
    IDocumentoSoporteService documentoSoporteService;

    @PostMapping("/guardarArchivo")
    public ResponseEntity<?> save(@RequestParam("file") MultipartFile file) {
        try {
            // Guardar el archivo y obtener la dirección
            String direccionDocumento = documentoSoporteService.save(file);

            // Crear la entidad DocumentoSoporte con la información del archivo
            DocumentoSoporte documentoSoporte = new DocumentoSoporte();
            documentoSoporte.setNombreDocumento(file.getOriginalFilename());
            documentoSoporte.setDireccionDocumento(direccionDocumento);

            // Guardar la entidad DocumentoSoporte en la base de datos
            documentoSoporte = documentoSoporteService.saveDocumento(documentoSoporte);

            // Puedes devolver la entidad guardada o un mensaje de éxito, según tus necesidades
            return new ResponseEntity<>(documentoSoporte, HttpStatus.CREATED);
        } catch (Exception e) {
            // Manejar las excepciones adecuadamente
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    // Metodos ya definidos
    /* @PostMapping("/upload")
    public ResponseEntity<Response> uploadFiles(@RequestParam("files") List<MultipartFile> files) throws Exception {
        documentoSoporteService.save(files);
        return ResponseEntity.status(HttpStatus.OK).body(new Response("Archivos cargados correctamente"));

    }

    @GetMapping("/files/{filename:.+}")
    public ResponseEntity<Resource> getFile(@PathVariable String filename) throws Exception {
        Resource resource = documentoSoporteService.load(filename);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\""
                        + resource.getClass() + "\"")
                .body(resource);
    }

    @GetMapping("/files")
    public ResponseEntity<List<DocumentoSoporte>> getAllFile() throws Exception {
        List<DocumentoSoporte> files = documentoSoporteService.loadAll().map(path -> {
            String filename = path.getFileName().toString();
            String url = MvcUriComponentsBuilder.fromMethodName(FileFacturaController.class, "getFile",
                    path.getFileName().toString()).build().toString();
            return new DocumentoSoporte(filename, url);
        }).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(files);
    }*/
}

