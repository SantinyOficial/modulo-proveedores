package com.controlmas.proveedores.models.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name="pagos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pagos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPago;

    @Column(name="fecha_pago")
    private Date fechaPago;

    @Column(name = "valor_pagado")
    private Integer valor;

    @Column(name = "factura_estado")
    private Integer facturaEstado;



}
