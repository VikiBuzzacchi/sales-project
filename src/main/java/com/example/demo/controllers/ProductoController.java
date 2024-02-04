package com.example.demo.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.Producto;
import com.example.demo.service.ProductoService;

@RestController
// @RequestMapping("/productos")
public class ProductoController {

    @Autowired
    private ProductoService repo;

    /**
     * Get de producto
     * @return lista de productos de la base de datos
     */
    @GetMapping("productos")
    public List<Producto> getProductos() {
        return repo.getAllProductos();
    }

    /**
     * Post de producto
     * @param producto
     * @return producto nuevo guardado
     */
    @PostMapping("producto/nuevo")
    public String postP(@RequestBody Producto producto) {
        repo.createProducto(producto);
        return "Producto guardado.";
    }

    /**
     * Put de producto
     * @param id
     * @param producto
     * @return actualización del producto en base a su id
     */
    @PutMapping("producto/modificar/{id}")
    // public String updateP(@PathVariable Long id, @RequestBody Producto producto) {
    //     Producto updateProducto = repo.findById(id).get();
    //     updateProducto.setCantidad(producto.getCantidad());
    //     updateProducto.setDescripcion(producto.getDescripcion());
    //     updateProducto.setNombre(producto.getNombre());
    //     updateProducto.setPrecio(producto.getPrecio());
    //     updateProducto.setCodigo(producto.getCodigo());
    //     repo.save(updateProducto);
    //     return "Producto actualizado con éxito.";
    // }
    public ResponseEntity<String> updateP(@PathVariable Long id, @RequestBody Producto producto) {
        boolean encontrado = repo.updateProducto(id, producto);

        if (encontrado) {
            return new ResponseEntity<>("Producto actualizado con éxito.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Producto no encontrado.", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/modificar/precio/{id}")
    public ResponseEntity<String> updatePrecio(@PathVariable Long id, @RequestBody Producto producto) {
        return repo.updatePrecio(id, producto);
    }

    @PutMapping("/modificar/stock/{id}")
    public ResponseEntity<String> updateStock(@PathVariable Long id, @RequestBody Producto producto) {
        return repo.updateStock(id, producto);
    }

    /**
     * Put en producto
     * @param id
     * @return deja un producto sin stock(0)
     */
    @PutMapping("sinstock/{id}")
    public ResponseEntity<String> sinStock(@PathVariable Long id) {
        return repo.sinStock(id);
    }


    /**
     * Delete de producto
     * @param id
     * @return producto eliminado, de ser encontrado
     */
    @DeleteMapping("producto/elim/{id}")
    public ResponseEntity<String> deleteP(@PathVariable Long id) {
    
        boolean encontrado = repo.deleteProducto(id);

        if (encontrado) {
            return new ResponseEntity<>("Producto eliminado.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Producto no encontrado.", HttpStatus.NOT_FOUND);
        }
    }
}
