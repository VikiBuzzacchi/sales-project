package com.example.demo.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.Venta;
import com.example.demo.repository.VentaRepository;
import com.example.demo.service.VentaService;

@RestController
@RequestMapping("/ventas")
public class VentaController {
    @Autowired
    private VentaService repo;

    /**
     * Get de ventas
     * @return lista de ventas en la base de datos
     */
    @GetMapping
    public List<Venta> getVentas() {
        return repo.getAllVentas();
    }

    /**
     * Delete en ventas
     * @param id
     * @return venta eliminada en caso de ser encontrada
     */
    @DeleteMapping("/elim/{id}")
    public ResponseEntity<String> deleteP(@PathVariable Long id) {
    
        boolean encontrado = repo.deleteVenta(id);

        if (encontrado) {
            return new ResponseEntity<>("Venta eliminada.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Venta no encontrada.", HttpStatus.NOT_FOUND);
        }
    }
}
