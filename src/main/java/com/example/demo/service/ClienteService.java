package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.Cliente;
import com.example.demo.repository.ClienteRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> getAllClientes() {
        return clienteRepository.findAll();
    }

    public Cliente createCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public Cliente getClienteById(Long clienteId) {
        return clienteRepository.findById(clienteId)
                .orElseThrow(() -> new EntityNotFoundException("Cliente no encontrado con ID: " + clienteId));
    }

    public boolean updateCliente(Long clienteId, Cliente cliente) {
        // Validar si el cliente existe antes de actualizar
        // getClienteById(clienteId);
        // cliente.setId(clienteId);
        // return clienteRepository.save(cliente);
        //
        Optional<Cliente> optionalCliente = clienteRepository.findById(clienteId);
        boolean presente = false; // Cliente no encontrado

        if (optionalCliente.isPresent()) {
            getClienteById(clienteId);
            cliente.setId(clienteId);
            clienteRepository.save(cliente);
            presente = true;
        }
        return presente;
    }

    public boolean deleteCliente(Long clienteId) {
        // Validar si el cliente existe antes de eliminar
        Optional<Cliente> optionalCliente = clienteRepository.findById(clienteId);
        boolean presente = false; // Cliente no encontrado

        if (optionalCliente.isPresent()) {
            Cliente deleteCliente = optionalCliente.get();
            clienteRepository.delete(deleteCliente);
            presente = true; // Cliente eliminado con éxito
        }
        return presente;
    }
}
