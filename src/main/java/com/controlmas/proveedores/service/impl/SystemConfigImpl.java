package com.controlmas.proveedores.service.impl;

import com.controlmas.proveedores.models.entity.SystemConfig;
import com.controlmas.proveedores.repository.ISystemConfigRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SystemConfigImpl {

    private final ISystemConfigRepository systemConfigRepository;

    @Autowired
    public SystemConfigImpl(ISystemConfigRepository systemConfigRepository) {
      this.systemConfigRepository = systemConfigRepository;
    }

    public String getComandoByOrigen(String origen){

      SystemConfig systemConfig = systemConfigRepository.findByOrigen(origen);

      if (systemConfig != null){
        return systemConfig.getComando();
      }else{
        return null;
      }
    }

}
