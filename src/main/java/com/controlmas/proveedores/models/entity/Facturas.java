package com.controlmas.proveedores.models.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @Column(name = "id_usuario")
    private int usuario;

    @Column(name = "fecha_creacion")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date fechaCreacion;

    @Column(name = "fecha_estado")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime fechaEstado;

    @Column(name = "estado_factura")
    private Short estado;

    @Column(name = "estado_string")
    private String estadoStr;

    @Column(name = "valor_factura")
    private Double valorFactura;

    @Column(name = "valor_pago")
    private Double valorPago;

    @Column(name = "saldo_factura")
    private Double saldoFactura;

    @Column(name = "valor_iva")
    private Double iva;

    @ManyToOne
    @JoinColumn(name = "id_prov")
    private Proveedores proveedor;

    @OneToMany(mappedBy = "factura", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Pagos> pagos;

    public Facturas(Integer idFactura){
        this.idFactura = idFactura;
    }

    @PreUpdate
    public void preUpdate() {
        this.fechaEstado = LocalDateTime.now();
    }

    @PrePersist
    public void prePersist() {
        this.fechaCreacion = new Date();
    }



}
