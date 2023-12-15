package com.controlmas.proveedores.repository;

import com.controlmas.proveedores.models.entity.Pagos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPagoRepository extends JpaRepository<Pagos, Integer> {
}
