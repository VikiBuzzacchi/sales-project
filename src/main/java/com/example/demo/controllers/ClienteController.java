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

import com.example.demo.models.Cliente;
import com.example.demo.service.ClienteService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

@RestController
public class ClienteController {

    // me da métodos de jpa
    // abstraernos de la sintaxis de sql
    @Autowired
    private ClienteService repo;

    /**
     * 
     * @return index
     */
    @Operation(summary = "Index", description = "Inicio de la aplicación")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa"),
            @ApiResponse(responseCode = "400", description = "Parámetros incorrectos", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDTO.class)))
    })
    @GetMapping
    public String index() {
        return "Conectando";
    }

    /**
     * Get de clientes
     * 
     * @return lista completa de clientes en la base de datos
     */
    @Operation(summary = "Mostrar lista de usuarios", description = "Permite mostrar la lista de clientes de la base de datos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa"),
            @ApiResponse(responseCode = "400", description = "Parámetros incorrectos", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDTO.class)))
    })
    @GetMapping("clientes")
    public List<Cliente> getClientes() {
        return repo.getAllClientes();
    }

    /**
     * Post de cliente
     * 
     * @param cliente
     * @return cliente guardado
     */
    @Operation(summary = "Crear nuevo usuario", description = "Permite crear un nuevo usuario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa"),
            @ApiResponse(responseCode = "400", description = "Parámetros incorrectos", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDTO.class)))
    })
    @PostMapping("alta")
    public String post(@RequestBody Cliente cliente) {
        repo.createCliente(cliente);
        return "Cliente guardado.";
    }

    /**
     * Put de cliente
     * 
     * @param id
     * @param cliente
     * @return cliente modificado
     */
    @Operation(summary = "Modificar un cliente en base a su ID", description = "Se pasa el ID del cliente y se modifica la información del mismo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa"),
            @ApiResponse(responseCode = "400", description = "Parámetros incorrectos", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDTO.class)))
    })
    @PutMapping("modificar/{id}")
    // public String update(@PathVariable Long id, @RequestBody Cliente cliente) {
    public ResponseEntity<String> update(@PathVariable Long id, @RequestBody Cliente cliente) {
        boolean encontrado = repo.updateCliente(id, cliente);

        if (encontrado) {
            return new ResponseEntity<>("Cliente modificado.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Cliente no encontrado.", HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Delete de cliente
     * 
     * @param id
     * @return si se pudo o no eliminar el cliente
     */
    @Operation(summary = "Eliminar usuario por ID", description = "En base al ID del cliente, y si el mismo existe, se puede eliminar de la BD")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa"),
            @ApiResponse(responseCode = "400", description = "Parámetros incorrectos", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDTO.class)))
    })
    @DeleteMapping("baja/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        boolean encontrado = repo.deleteCliente(id);

        if (encontrado) {
            return new ResponseEntity<>("Cliente eliminado.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Cliente no encontrado.", HttpStatus.NOT_FOUND);
        }
    }

}
