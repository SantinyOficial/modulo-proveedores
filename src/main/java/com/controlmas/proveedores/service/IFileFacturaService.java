package com.controlmas.proveedores.service;

import jakarta.annotation.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

public interface IFileFacturaService {

    public void save(MultipartFile file) throws Exception;

    public Resource load(String name) throws Exception;

    public void save(List<MultipartFile> files) throws Exception;

    public Stream<Path> loadAll() throws Exception;


}


