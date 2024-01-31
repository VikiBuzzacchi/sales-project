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

import com.example.demo.models.Cliente;
import com.example.demo.repository.ClienteRepository;

@RestController
public class ClienteController {

    // me da métodos de jpa
    // abstraernos de la sintaxis de sql
    @Autowired
    private ClienteRepository repo;
    
    @GetMapping
    public String index() {
        return "Conectando";
    }

    @GetMapping("clientes")
    public List<Cliente> getClientes() {
        return repo.findAll();
    }

    @PostMapping("alta")
    public String post(@RequestBody Cliente cliente) {
        repo.save(cliente);
        return "Cliente guardado.";
    }

    @PutMapping("modificar/{id}")
    // public String update(@PathVariable Long id, @RequestBody Cliente cliente) {
    public Cliente update(@PathVariable Long id, @RequestBody Cliente cliente) {
        Cliente updateCliente = repo.findById(id).get();
        updateCliente.setNombre(cliente.getNombre());
        updateCliente.setEmail(cliente.getEmail());
        updateCliente.setApellido(cliente.getApellido());
        updateCliente.setDni(cliente.getDni());
        repo.save(updateCliente);
        // return "Modificado";
        return updateCliente;
    }

    @DeleteMapping("baja/{id}")
    public String delete(@PathVariable Long id) {
        Cliente deleteCliente = repo.findById(id).get();
        repo.delete(deleteCliente);
        return "Cliente eliminado.";
    }
}
