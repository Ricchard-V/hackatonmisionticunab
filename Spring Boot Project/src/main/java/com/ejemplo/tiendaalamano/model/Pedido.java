package com.ejemplo.tiendaalamano.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.springframework.data.annotation.CreatedDate;



@Entity(name = "pedidos")
public class Pedido implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    long id;

    @CreatedDate
    Date fecha;

    @OneToOne
    @JoinColumn(name ="cliente_id")
    private Cliente cliente;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pedidoModel")
    private List<pedido_detalle> detalleList = new ArrayList<>();

}
