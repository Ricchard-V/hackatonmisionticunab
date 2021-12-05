package com.ejemplo.tiendaalamano.service;

import java.util.List;
import java.util.Optional;

import com.ejemplo.tiendaalamano.exception.BadResourceRequestException;
import com.ejemplo.tiendaalamano.model.Producto;
import com.ejemplo.tiendaalamano.repository.ProductosRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductoServiceImpl implements ProductoService  {

    @Autowired 
    private ProductosRepository productosRepository;

    @Transactional
    @Override
    public void delete(Long id) {
      productosRepository.deleteById(id);  
        
    }

    @Override
    @Transactional
    public List<Producto> findAll() {
        return (List) productosRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Producto findById(Long id) {
        
        return productosRepository.findById(id).orElse(null);

    }

    @Override
    @Transactional
    public Producto save(Producto producto) {

        return productosRepository.save(producto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Producto> findAll(org.springframework.data.domain.Pageable pageable) {
        return productosRepository.findAll(pageable);

    }

    @Override
    public void createProducto(Producto producto) {
        Optional<Producto> existingModel = productosRepository.findById(producto.getId());

        if (!existingModel.isPresent()) {
            throw new BadResourceRequestException("Producto Existente.");
        }
        productosRepository.save(producto);
    }
}
