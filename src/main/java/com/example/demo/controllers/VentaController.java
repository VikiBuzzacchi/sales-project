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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.Venta;
import com.example.demo.service.VentaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
// import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/ventas")
public class VentaController {
    @Autowired
    private VentaService repo;

    /**
     * Get de ventas
     * 
     * @return lista de ventas en la base de datos
     */
    @Operation(summary = "Mostrar lista de ventas", description = "Permite mostrar la lista de ventas de la base de datos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa"),
            @ApiResponse(responseCode = "400", description = "Parámetros incorrectos",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDTO.class)))
    })
    @GetMapping
    public List<Venta> getVentas() {
        return repo.getAllVentas();
    }

    /**
     * Delete en ventas
     * 
     * @param id
     * @return venta eliminada en caso de ser encontrada
     */
    @Operation(summary = "Eliminar venta por ID", description = "En base al ID de la venta, y si el mismo existe, se puede eliminar el mismo de la BD")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa"),
            @ApiResponse(responseCode = "400", description = "Parámetros incorrectos",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDTO.class)))
    })
    @DeleteMapping("/elim/{id}")
    public ResponseEntity<String> deleteV(@PathVariable Long id) {

        boolean encontrado = repo.deleteVenta(id);

        if (encontrado) {
            return new ResponseEntity<>("Venta eliminada.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Venta no encontrada.", HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Crear nueva venta", description = "Permite crear una nueva venta")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa"),
            @ApiResponse(responseCode = "400", description = "Parámetros incorrectos",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDTO.class)))
    })
    @PostMapping("/new")
    // public String postV(@RequestBody Venta venta) {
    // repo.createVenta(venta);
    // return "Venta guardada.";
    // }
    // public ResponseEntity<String> postV(@RequestBody Venta venta) {

    //     Venta nuevaVenta = repo.createVenta(venta);

    //     if (nuevaVenta != null) {
    //         return new ResponseEntity<>("Venta guardada.", HttpStatus.OK);
    //     } else {
    //         return new ResponseEntity<>("No se pudo guardar la venta.", HttpStatus.INTERNAL_SERVER_ERROR);
    //     }
    // }
    public String postV(@RequestBody Venta venta) {
        repo.createVenta(venta);
        return "Venta guardada";
    }

    @Operation(summary = "Modificar una venta en base a su ID", description = "Se pasa el ID de la venta y se modifica la información de la misma")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa"),
            @ApiResponse(responseCode = "400", description = "Parámetros incorrectos",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDTO.class)))
    })
    @PutMapping("/modificar/{id}")
    public ResponseEntity<String> updateV(@PathVariable Long id, @RequestBody Venta venta) {
        boolean encontrado = repo.updateVenta(id, venta);

        if (encontrado) {
            return new ResponseEntity<>("Venta actualizado con éxito.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Venta no encontrado.", HttpStatus.NOT_FOUND);
        }
    }
}
