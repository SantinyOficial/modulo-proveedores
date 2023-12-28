package com.controlmas.proveedores.service;

import com.controlmas.proveedores.models.entity.Pagos;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;


public interface IPagoService {

    Pagos save(Pagos pago, MultipartFile file);

    Optional<Pagos> findById(Integer id);

    List<Pagos> findAll();
}
