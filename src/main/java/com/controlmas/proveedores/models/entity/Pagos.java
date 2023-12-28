package com.controlmas.proveedores.models.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "pagos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pagos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPago;

    @Column(name = "id_usuario")
    private int idUsuario;

    @ManyToOne
    @JoinColumn(name = "id_factura")
    private Facturas factura;

    @Column(name = "fecha_creacion")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date fechaCreacion;

    @Column(name = "fecha_estado")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime fechaEstado;

    @Column(name = "valor_pago")
    private Double valorPago;

    @Column(name = "aplicado")
    private boolean aplicado;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_documento_soporte")
    private DocumentoSoporte documentoSoporte;

    @PreUpdate
    public void preUpdate() {
        this.fechaEstado = LocalDateTime.now();
    }

    @PrePersist
    public void prePersist() {
        this.fechaCreacion = new Date();
    }
}
