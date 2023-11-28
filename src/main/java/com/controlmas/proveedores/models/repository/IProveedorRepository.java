package com.controlmas.proveedores.models.repository;

import com.controlmas.proveedores.models.entity.Proveedores;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProveedorRepository extends JpaRepository<Proveedores, Integer> {


}
