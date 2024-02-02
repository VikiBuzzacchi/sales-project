package com.example.demo.controllers;

import java.util.List;
import java.util.Optional;

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
import com.example.demo.repository.ClienteRepository;
import com.example.demo.service.ClienteService;

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
    @GetMapping
    public String index() {
        return "Conectando";
    }

    /**
     * Get de clientes
     * @return lista completa de clientes en la base de datos
     */
    @GetMapping("clientes")
    public List<Cliente> getClientes() {
        return repo.getAllClientes();
    }

    /**
     * Post de cliente
     * @param cliente
     * @return cliente guardado
     */
    @PostMapping("alta")
    public String post(@RequestBody Cliente cliente) {
        repo.createCliente(cliente);
        return "Cliente guardado.";
    }

    /**
     * Put de cliente
     * @param id
     * @param cliente
     * @return cliente modificado
     */
    @PutMapping("modificar/{id}")
    // public String update(@PathVariable Long id, @RequestBody Cliente cliente) {
    public ResponseEntity<String> update(@PathVariable Long id, @RequestBody Cliente cliente) {
        // Cliente updateCliente = repo.updateCliente(id, cliente);
        // // return "Modificado";
        // return updateCliente;

        //
        boolean encontrado = repo.updateCliente(id, cliente);

        if (encontrado) {
            return new ResponseEntity<>("Cliente modificado.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Cliente no encontrado.", HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Delete de cliente
     * @param id
     * @return si se pudo o no eliminar el cliente
     */
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
