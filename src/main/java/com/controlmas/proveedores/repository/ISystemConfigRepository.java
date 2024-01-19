package com.controlmas.proveedores.repository;

import com.controlmas.proveedores.models.entity.SystemConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISystemConfigRepository extends JpaRepository<SystemConfig, Integer> {

    SystemConfig findByOrigen(String origen);

}
