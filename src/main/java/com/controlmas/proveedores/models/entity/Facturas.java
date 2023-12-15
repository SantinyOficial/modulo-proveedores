package com.controlmas.proveedores.models.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "facturas")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Facturas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_factura")
    private Integer idFactura;

    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion;

    private Double valor;

    private Integer iva;

    private Short estado;

    @ManyToOne
    @JoinColumn(name = "id_prov")
    private Proveedores idProveedor;

    @OneToMany(mappedBy = "factura", cascade = CascadeType.ALL)
    private List<Pagos> pagos;

}
