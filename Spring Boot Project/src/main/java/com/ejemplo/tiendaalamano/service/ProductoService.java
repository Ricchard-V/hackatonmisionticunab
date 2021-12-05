package com.ejemplo.tiendaalamano.service;

import java.util.List;

import com.ejemplo.tiendaalamano.model.Producto;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductoService {
    
    public List<Producto> findAll();
    public Page<Producto> findAll(Pageable pageable);
    public Producto findById(Long id);
    public Producto save(Producto producto);
    public Producto createProducto(Producto producto);
    public void delete(Long id);




}
