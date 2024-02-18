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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
// @RequestMapping("/productos")
public class ProductoController {

    @Autowired
    private ProductoService repo;

    /**
     * Get de producto
     * 
     * @return lista de productos de la base de datos
     */
    @Operation(summary = "Mostrar lista de productos", description = "Permite mostrar la lista de productos de la base de datos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa"),
            @ApiResponse(responseCode = "400", description = "Parámetros incorrectos", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDTO.class)))
    })
    @GetMapping("productos")
    public List<Producto> getProductos() {
        return repo.getAllProductos();
    }

    /**
     * Post de producto
     * 
     * @param producto
     * @return producto nuevo guardado
     */
    @Operation(summary = "Crear nuevo producto", description = "Permite crear un nuevo producto")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa"),
            @ApiResponse(responseCode = "400", description = "Parámetros incorrectos", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDTO.class)))
    })
    @PostMapping("producto/new")
    public String postP(@RequestBody Producto producto) {
        repo.createProducto(producto);
        return "Producto guardado.";
    }

    /**
     * Put de producto
     * 
     * @param id
     * @param producto
     * @return actualización del producto en base a su id
     */
    @Operation(summary = "Modificar un producto en base a su ID", description = "Se pasa el ID del producto y se modifica la información del mismo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa"),
            @ApiResponse(responseCode = "400", description = "Parámetros incorrectos", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDTO.class)))
    })
    @PutMapping("producto/modificar/{id}")
    public ResponseEntity<String> updateP(@PathVariable Long id, @RequestBody Producto producto) {
        boolean encontrado = repo.updateProducto(id, producto);

        if (encontrado) {
            return new ResponseEntity<>("Producto actualizado con éxito.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Producto no encontrado.", HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Modificar el precio un producto en base a su ID", description = "Se pasa el ID del producto y se modifica la el precio del mismo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa"),
            @ApiResponse(responseCode = "400", description = "Parámetros incorrectos", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDTO.class)))
    })
    @PutMapping("/modificar/precio/{id}")
    public ResponseEntity<String> updatePrecio(@PathVariable Long id, @RequestBody Producto producto) {
        return repo.updatePrecio(id, producto);
    }

    @Operation(summary = "Modificar el stock de un producto en base a su ID", description = "Se pasa el ID del producto y se modifica el stock del mismo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa"),
            @ApiResponse(responseCode = "400", description = "Parámetros incorrectos", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDTO.class)))
    })
    @PutMapping("/modificar/stock/{id}")
    public ResponseEntity<String> updateStock(@PathVariable Long id, @RequestBody Producto producto) {
        return repo.updateStock(id, producto);
    }

    /**
     * Put en producto
     * 
     * @param id
     * @return deja un producto sin stock(0)
     */
    @Operation(summary = "Dejar sin stock un producto", description = "Se pasa el ID del producto permitiendo que este se muestre sin stock, es decir con cantidad 0")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa"),
            @ApiResponse(responseCode = "400", description = "Parámetros incorrectos", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDTO.class)))
    })
    @PutMapping("sinstock/{id}")
    public ResponseEntity<String> sinStock(@PathVariable Long id) {
        return repo.sinStock(id);
    }

    /**
     * Delete de producto
     * 
     * @param id
     * @return producto eliminado, de ser encontrado
     */
    @Operation(summary = "Eliminar producto por ID", description = "En base al ID del producto, y si el mismo existe, se puede eliminar de la BD")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa"),
            @ApiResponse(responseCode = "400", description = "Parámetros incorrectos", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDTO.class)))
    })
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
