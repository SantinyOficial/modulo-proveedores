package com.controlmas.proveedores.models.entity;

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
    private Integer id;

    @NotEmpty
    @Column(name = "nombre_prov")
    private String nombreProveedor;

    @NotEmpty
    @Column(name = "direccion_prov")
    private String direccionProveedor;

    @NotEmpty
    @Column(name = "correo_prov")
    private String correoProveedor;

    @NotEmpty
    @Column(name = "telefono_prov")
    private Long telefonoProveedor;

    @NotNull
    @Column(name = "fecha_registro")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fechaRegistro;

    @OneToMany(mappedBy = "proveedor", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Facturas> facturas;

}
