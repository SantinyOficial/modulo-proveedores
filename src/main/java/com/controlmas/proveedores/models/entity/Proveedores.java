package com.controlmas.proveedores.models.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;


import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;


@Entity
@Table(name = "proveedores")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Proveedores {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_prov")
    private Integer idProveedor;

    @NotEmpty
    @Column(name = "nombre_pr")
    private String nombreProveedor;

    @NotEmpty
    @Column(name = "direccion_pr")
    private String direccionProveedor;

    @NotEmpty
    @Column(name = "correo_pr")
    private String correoProveedor;

    @NotEmpty
    @Column(name = "telefono_pr")
    private Long telefonoProveedor;

    @Column(name = "fecha_creacion")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date fechaCreacion;

    @OneToMany(mappedBy = "proveedor", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Facturas> facturas;

    @PrePersist
    public void prePersist() {
        this.fechaCreacion = new Date();
    }

}
