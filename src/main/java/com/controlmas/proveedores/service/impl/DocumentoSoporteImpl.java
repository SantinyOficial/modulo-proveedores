package com.controlmas.proveedores.service.impl;

import com.controlmas.proveedores.models.entity.DocumentoSoporte;
import com.controlmas.proveedores.repository.IDocumentoSoporteRepository;
import com.controlmas.proveedores.service.IDocumentoSoporteService;
import jakarta.annotation.Resource;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

@Service
public class DocumentoSoporteImpl implements IDocumentoSoporteService {

    @Autowired
    IDocumentoSoporteRepository documentoSoporteRepository;

    private final Path rootFolder = Paths.get("uploads");

    @Transactional
    @Override
    public DocumentoSoporte saveDocumento(DocumentoSoporte documentoSoporte) {
        return documentoSoporteRepository.save(documentoSoporte);
    }

    @Transactional
    @Override
    public String save(MultipartFile file) throws Exception {
        // Genera un nombre único para el archivo
        String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();

        // Copia el archivo al directorio de carga
        Path filePath = this.rootFolder.resolve(fileName);
        Files.copy(file.getInputStream(), filePath);

        // Crea la entidad DocumentoSoporte y guárdala en la base de datos
        DocumentoSoporte documentoSoporte = new DocumentoSoporte();
        documentoSoporte.setNombreDocumento(file.getOriginalFilename());
        documentoSoporte.setDireccionDocumento(filePath.toString());
        documentoSoporteRepository.save(documentoSoporte);

        // Devuelve la ruta del archivo guardado
        return filePath.toString();
    }

    @Override
    public Resource load(String name) throws Exception {
        Path file = rootFolder.resolve(name);
        Resource resource = (Resource) new UrlResource(file.toUri());
        return resource;
    }

    @Override
    public void save(List<MultipartFile> files) throws Exception {
        for (MultipartFile multipartFile : files){
            this.saveDocumento((DocumentoSoporte) multipartFile);
        }
    }

    @Override
    public Stream<Path> loadAll() throws Exception {
        return Files.walk(rootFolder, 1)
                .filter(path -> !path.equals(rootFolder))
                .map(rootFolder::relativize);
    }
}
