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

import com.example.demo.models.Venta;
import com.example.demo.repository.VentaRepository;

@RestController
public class VentaController {
    @Autowired
    private VentaRepository repo;

    @GetMapping("ventas")
    public List<Venta> getVentas() {
        return repo.findAll();
    }

}
