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

import com.example.demo.models.Venta;
import com.example.demo.models.Cliente;
import com.example.demo.repository.VentaRepository;

@RestController
public class VentaController {
    @Autowired
    private VentaRepository repo;

    @GetMapping("ventas")
    public List<Venta> getVentas() {
        return repo.findAll();
    }

    @DeleteMapping("venta/elim/{id}")
    public ResponseEntity<String> deleteV(@PathVariable Long id) {
        Optional<Venta> optionalVenta = repo.findById(id);

        if (optionalVenta.isPresent()) {
            Venta deleteVenta = optionalVenta.get();
            repo.delete(deleteVenta);
            return new ResponseEntity<>("Venta eliminada.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Venta no encontrada.", HttpStatus.NOT_FOUND);
        }
    }

}
