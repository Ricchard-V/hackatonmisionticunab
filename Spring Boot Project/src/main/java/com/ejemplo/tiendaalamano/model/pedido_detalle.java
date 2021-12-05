package com.ejemplo.tiendaalamano.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;


@Entity(name = "detalle_pedidos")
public class pedido_detalle  implements Serializable{

    private static final long serialVersionUID = 1l;


    @Id
    @GeneratedValue
    private long id;
      
    @Column(nullable = false)
    private int cantidad;
    
    @Column(nullable = false)
    private int subtotal;

    @Column(nullable = false)
    private int iva;

    @Column(nullable = false)
    private int total_detalle;

    @OneToOne
    @JoinColumn(name="producto_id")
    private Producto producto;

    @ManyToOne
    @JoinColumn(name = "pedido_id")
    private Pedido pedidoModel; 




    




    




    
}
