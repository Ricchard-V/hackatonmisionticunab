package com.ejemplo.tiendaalamano.repository;

import com.ejemplo.tiendaalamano.model.Producto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProductosRepository extends JpaRepository<Producto, Long>{
    
    
}
