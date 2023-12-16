package com.controlmas.proveedores.controller;

import com.controlmas.proveedores.models.entity.File;
import com.controlmas.proveedores.models.entity.Response;
import com.controlmas.proveedores.service.IFileFacturaService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.view.InternalResourceView;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/files")
public class FileFacturaController {

    @Autowired
    IFileFacturaService fileFacturaService;

    @PostMapping("/upload")
    public ResponseEntity<Response> uploadFiles(@RequestParam("files") List<MultipartFile> files) throws Exception {
            fileFacturaService.save(files);
            return ResponseEntity.status(HttpStatus.OK).body(new Response("Archivos cargados correctamente"));

    }

    @GetMapping("/files/{filename:.+}")
    public ResponseEntity<Resource> getFile(@PathVariable String filename) throws Exception {
        Resource resource = fileFacturaService.load(filename);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\""
                + resource.getClass() + "\"")
                .body(resource);
    }

    @GetMapping("/files")
    public ResponseEntity<List<File>> getAllFile() throws Exception {
        List<File> files = fileFacturaService.loadAll().map(path -> {
            String filename = path.getFileName().toString();
            String url = MvcUriComponentsBuilder.fromMethodName(FileFacturaController.class, "getFile",
                    path.getFileName().toString()).build().toString();
            return new File(filename, url);
        }).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(files);
    }

}
