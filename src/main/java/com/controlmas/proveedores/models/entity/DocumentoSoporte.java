package com.controlmas.proveedores.models.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Entity
@Table(name = "documento_soporte")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DocumentoSoporte {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_documento_soporte")
    private Integer idDocumento;

    @Column(name = "nombre_documento")
    private String nombreDocumento;

    @Column(name = "direccion_documento")
    private String direccionDocumento;

}
