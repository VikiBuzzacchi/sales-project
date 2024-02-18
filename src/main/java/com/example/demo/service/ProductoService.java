package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.models.Producto;
import com.example.demo.repository.ProductoRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository repo;

    public List<Producto> getAllProductos() {
        return repo.findAll();
    }

    public Producto createProducto(Producto producto) {
        return repo.save(producto);
    }

    public boolean updateProducto(Long productoId, Producto producto) {
        Optional<Producto> optionalProducto = repo.findById(productoId);
        boolean presente = false; // Producto no encontrado

        if (optionalProducto.isPresent()) {
            getProductoById(productoId);
            producto.setId(productoId);
            repo.save(producto);
            presente = true;
        }
        return presente;
    }

    public Producto getProductoById(Long productoId) {
        return repo.findById(productoId)
                .orElseThrow(() -> new EntityNotFoundException("Producto no encontrado con ID: " + productoId));
    }

    public List<Producto> getProductosByIds(List<Long> ids) {
        return repo.findAllById(ids);
    }

    // sets particulares

    public ResponseEntity<String> updatePrecio(Long id, Producto producto) {
        return updateField(id, producto, "precio", producto.getPrecio());
    }

    public ResponseEntity<String> updateStock(Long id, Producto producto) {
        return updateField(id, producto, "cantidad", producto.getCantidad());
    }

    public ResponseEntity<String> sinStock(Long id) {
        return updateField(id, new Producto(), "cantidad", 0);
    }

    private ResponseEntity<String> updateField(Long id, Producto producto, String fieldName, Object fieldValue) {
        Optional<Producto> optionalProducto = repo.findById(id);

        if (optionalProducto.isPresent()) {
            Producto updateProducto = optionalProducto.get();
            if ("precio".equals(fieldName)) {
                updateProducto.setPrecio((Double) fieldValue);
            } else if ("cantidad".equals(fieldName)) {
                updateProducto.setCantidad((Integer) fieldValue);
            } else {
                return new ResponseEntity<>("Campo no válido.", HttpStatus.BAD_REQUEST);
            }
            repo.save(updateProducto);
            return new ResponseEntity<>(fieldName + " actualizado con éxito.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Producto no encontrado.", HttpStatus.NOT_FOUND);
        }
    }

    public boolean deleteProducto(Long productoId) {
        // Validar si el producto existe antes de eliminar
        Optional<Producto> optionalProducto = repo.findById(productoId);
        boolean presente = false; // Producto no encontrado

        if (optionalProducto.isPresent()) {
            Producto deleteProducto = optionalProducto.get();
            repo.delete(deleteProducto);
            presente = true; // Producto eliminado con éxito
        }
        return presente;
    }

}
