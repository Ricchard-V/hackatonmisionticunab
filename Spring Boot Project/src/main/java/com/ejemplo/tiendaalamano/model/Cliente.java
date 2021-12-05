package com.ejemplo.tiendaalamano.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity(name = "clientes")
public class Cliente implements Serializable {

    private static final long serialVersionUID = 1l;

    @Id
    @GeneratedValue
    long id;

    @Column(nullable = false, length = 50 )
    String nombres;
    
    @Column(nullable = false, length = 50 )
    String apellidos;


    
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    @Column(nullable = false)
    Integer edad;

    @Column(nullable = false, length = 50 )
    String genero;





     
}
