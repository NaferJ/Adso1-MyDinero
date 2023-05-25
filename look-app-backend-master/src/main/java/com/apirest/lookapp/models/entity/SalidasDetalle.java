package com.apirest.lookapp.models.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "salidas_detalle")
public class SalidasDetalle implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Size(max = 45)
    @Column(name = "dcto")
    private String dcto;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "valor_pagado")
    private Double valorPagado;
    @JoinColumn(name = "id_bien_servicio", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private BienesServicios idBienServicio;
    @JoinColumn(name = "id_puesto_trabajo", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private PuestosTrabajo idPuestoTrabajo;
    @JoinColumn(name = "id_salida", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Salidas idSalida;
}
