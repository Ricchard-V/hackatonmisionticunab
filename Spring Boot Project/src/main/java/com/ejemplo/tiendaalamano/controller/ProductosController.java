package com.ejemplo.tiendaalamano.controller;

import org.springframework.web.bind.annotation.RestController;

import java.text.Collator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.ejemplo.tiendaalamano.model.Cliente;
import com.ejemplo.tiendaalamano.model.Producto;
import com.ejemplo.tiendaalamano.service.ProductoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/productos")
public class ProductosController {

    @Autowired
    private ProductoService  productoService;


    
    @GetMapping
    public List<Producto> list(){
        return productoService.findAll();
    }

//retorna todos los productos paginados 
    @GetMapping("productos/page/{page}")
    public Page<Producto> index(@PathVariable Integer page){
        Pageable pageable = PageRequest.of(page, 5);
        return productoService.findAll(pageable);
    }

//clase para crear 
    @RequestMapping(value = "/model", method = RequestMethod.POST, consumes = "application/json")
    
    @ResponseStatus(HttpStatus.CREATED)
    public void createNewModel(@RequestBody @Validated Producto producto) {
        productoService.createProducto(producto);  
    }


//clase show . busca por id del producto
    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable long id){
        Producto producto = null;

        Map<String,Object> response = new HashMap<>();

        try {
           producto = productoService.findById(id);

        }catch(DataAccessException e) {
            response.put( "mensaje",e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()) );
            return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        } 
        if(producto == null){
            response.put( "mensaje","El Producto No existe");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);      
        }

        return new ResponseEntity<Producto>(producto,HttpStatus.OK);
    }


    //metodo para actualizar 
    @PutMapping(value="/{id}")
    public ResponseEntity<?> put(@Validated @RequestBody Producto producto, BindingResult result, @PathVariable Long id) {
            Producto productoActual =  productoService.findById(id);
            Producto productoUpdated = null;

            Map<String,Object> response = new HashMap<>();

            if(result.hasErrors()){
                List<String> errors = result.getFieldErrors()
                                    .stream()
                                    .map(err->"error con el campo ' " + err.getField() + " ' "+ err.getDefaultMessage())
                                    .collect(Collectors.toList());
                
                response.put("errors", errors);
                return new ResponseEntity<Map<String,Object>>(response, HttpStatus.BAD_REQUEST);
            }

            try{
                productoActual.setNombre(producto.getNombre());
                productoActual.setDescripcion(producto.getDescripcion());
                productoActual.setCantidad(producto.getCantidad());
                productoActual.setValor(producto.getValor());
                productoActual.setIva(producto.getIva());
                productoUpdated = productoService.save(productoActual);
                
                
            }catch(DataAccessException e) {
                response.put( "mensaje",e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()) );
                return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            
            response.put("mensaje", "Actualizado con Exito");
            response.put("producto", productoUpdated);
            return new ResponseEntity<Producto>(producto,HttpStatus.OK);
    }



    


}
