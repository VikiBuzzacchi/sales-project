package com.example.demo.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.Venta;
import com.example.demo.service.VentaService;

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
    @DeleteMapping("/elim/{id}")
    public ResponseEntity<String> deleteV(@PathVariable Long id) {

        boolean encontrado = repo.deleteVenta(id);

        if (encontrado) {
            return new ResponseEntity<>("Venta eliminada.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Venta no encontrada.", HttpStatus.NOT_FOUND);
        }
    }

    // @PostMapping("/new")
    // // public String postV(@RequestBody Venta venta) {
    // // repo.createVenta(venta);
    // // return "Venta guardada.";
    // // }
    // public ResponseEntity<String> postV(@RequestBody Venta venta) {

    //     Venta nuevaVenta = repo.createVenta(venta);

    //     if (nuevaVenta != null) {
    //         return new ResponseEntity<>("Venta guardada.", HttpStatus.OK);
    //     } else {
    //         return new ResponseEntity<>("No se pudo guardar la venta.", HttpStatus.INTERNAL_SERVER_ERROR);
    //     }
    // }

    // @PutMapping("/modificar/{id}")
    // public ResponseEntity<String> updateV(@PathVariable Long id, @RequestBody Venta venta) {
    //     boolean encontrado = repo.updateVenta(id, venta);

    //     if (encontrado) {
    //         return new ResponseEntity<>("Venta actualizado con éxito.", HttpStatus.OK);
    //     } else {
    //         return new ResponseEntity<>("Venta no encontrado.", HttpStatus.NOT_FOUND);
    //     }
    // }
}
