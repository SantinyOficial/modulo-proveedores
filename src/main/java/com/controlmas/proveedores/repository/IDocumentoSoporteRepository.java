package com.controlmas.proveedores.repository;

import com.controlmas.proveedores.models.entity.DocumentoSoporte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDocumentoSoporteRepository extends JpaRepository<DocumentoSoporte, Integer> {
}
