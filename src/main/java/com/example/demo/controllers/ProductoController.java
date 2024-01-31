package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.Producto;
import com.example.demo.repository.ProductoRepository;

@RestController
public class ProductoController {
    
    @Autowired
    private ProductoRepository repo;

    @GetMapping("productos")
    public List<Producto> getProductos() {
        return repo.findAll();
    }

    @PostMapping("producto/nuevo")
    public String postP(@RequestBody Producto producto) {
        repo.save(producto);
        return "Producto guardado.";
    }

    @PutMapping("producto/modificar/{id}")
    public String updateP(@PathVariable Long id, @RequestBody Producto producto) {
        Producto updateProducto = repo.findById(id).get();
        updateProducto.setCantidad(producto.getCantidad());
        updateProducto.setDescripcion(producto.getDescripcion());
        updateProducto.setNombre(producto.getNombre());
        updateProducto.setPrecio(producto.getPrecio());
        updateProducto.setCodigo(producto.getCodigo());
        repo.save(updateProducto);
        return "Producto actualizado con éxito.";
    }

    @PutMapping("/modificar/precio/{id}")
    public String updatePrecio(@PathVariable Long id, @RequestBody Producto producto) {
        Producto updateProducto = repo.findById(id).get();
        updateProducto.setPrecio(producto.getPrecio());
        repo.save(updateProducto);
        return "Precio actualizado con éxito.";
    }

    @PutMapping("/modificar/stock/{id}")
    public String updateStock(@PathVariable Long id, @RequestBody Producto producto) {
        Producto updateProducto = repo.findById(id).get();
        updateProducto.setCantidad(producto.getCantidad());
        repo.save(updateProducto);
        return "Stock actualizado con éxito.";
    }
    
    @PutMapping("sinstock/{id}")
    public String sinStock(@PathVariable Long id) {
        Producto sinProducto = repo.findById(id).get();
        sinProducto.setCantidad(0);
        repo.save(sinProducto);
        return "Producto "+id+" sin stock.";
    }

    @DeleteMapping("producto/elim/{id}")
    public String deleteP(@PathVariable Long id) {
        Producto deleteProducto = repo.findById(id).get();
        repo.delete(deleteProducto);
        return "Producto liminado.";
    }
}
