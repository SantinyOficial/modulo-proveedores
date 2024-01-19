package com.controlmas.proveedores.controller;
import com.controlmas.proveedores.service.impl.FileStorageService;
import com.controlmas.proveedores.service.impl.SystemConfigImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api")
public class FileController {

    private final SystemConfigImpl systemConfigImpl;
    private final FileStorageService fileStorageService;

    @Autowired
    public FileController(SystemConfigImpl systemConfigService, FileStorageService fileStorageService) {
        this.systemConfigImpl = systemConfigService;
        this.fileStorageService = fileStorageService;
    }

    @PostMapping("/guardarDocumento")
    public ResponseEntity<String> GuardarArchivo(@RequestPart("archivo") MultipartFile archivo) {
        if (archivo.isEmpty()) {
            return new ResponseEntity<>("El archivo está vacío", HttpStatus.BAD_REQUEST);
        }

        String origen = "pagos";
        String ruta = systemConfigImpl.getComandoByOrigen(origen);

        if (ruta != null) {
            fileStorageService.storeFile(archivo, ruta);
            return new ResponseEntity<>("Archivo guardado exitosamente", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No se encontró la ruta en la base de datos", HttpStatus.NOT_FOUND);
        }
    }
}
