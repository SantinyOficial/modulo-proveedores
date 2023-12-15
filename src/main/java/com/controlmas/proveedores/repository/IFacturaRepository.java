package com.controlmas.proveedores.repository;

import com.controlmas.proveedores.models.entity.Facturas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IFacturaRepository extends JpaRepository<Facturas, Integer> {

}
