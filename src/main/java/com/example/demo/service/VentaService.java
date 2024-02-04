package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.Venta;
import com.example.demo.repository.VentaRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class VentaService {

    @Autowired
    private VentaRepository repo;

    public List<Venta> getAllVentas() {
        return repo.findAll();
    }

    public Venta createVenta(Venta venta) {
        return repo.save(venta);
    }

    public boolean updateVenta(Long ventaId, Venta venta) {
        Optional<Venta> optionalVenta = repo.findById(ventaId);
        boolean presente = false; // Venta no encontrado

        if (optionalVenta.isPresent()) {
            // getVentaById(ventaId);
            venta.setId(ventaId);
            repo.save(venta);
            presente = true;
        }
        return presente;
    }

    public Venta getVentaById(Long ventaId) {
        return repo.findById(ventaId)
                .orElseThrow(() -> new EntityNotFoundException("Venta no encontrado con ID: " + ventaId));
    }

    public boolean deleteVenta(Long ventaId) {
        // Validar si el venta existe antes de eliminar
        Optional<Venta> optionalVenta = repo.findById(ventaId);
        boolean presente = false; // Venta no encontrado

        if (optionalVenta.isPresent()) {
            Venta deleteVenta = optionalVenta.get();
            repo.delete(deleteVenta);
            presente = true; // Venta eliminado con éxito
        }
        return presente;
    }
}
