package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.Cliente;
import com.example.demo.models.Producto;
import com.example.demo.models.Venta;
import com.example.demo.models.VentaRequest;
import com.example.demo.repository.VentaRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class VentaService {

    @Autowired
    private VentaRepository repo;

    @Autowired
    private FechaRestApi fechaService; // Nuevo servicio para obtener la fecha del servicio externo

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ProductoService productoService;

    public List<Venta> getAllVentas() {
        return repo.findAll();
    }

    public Venta createVenta(VentaRequest venta) {
        Cliente clienteEncontrado = clienteService.getClienteById(venta.getClienteId());
        if (clienteEncontrado == null) {
            return null;
        }
        List<Long> productoIds = venta.getProductoIds();
        List<Producto> productosEncontrados;
        if (venta.getProductoIds() != null && !venta.getProductoIds().isEmpty()) {
            productosEncontrados = productoService.getProductosByIds(productoIds);
        } else {
            return null;
        }

        List<Producto> productosVendidos = new ArrayList<>();
        // ;

        for (Long productoId : productoIds) {
            for (Producto producto : productosEncontrados) {
                if (producto.getId() == productoId) {
                    productosVendidos.add(producto);
                }
            }
        }

        // Calcular el precio total sumando los precios de los productos
        float precioTotalVenta = 0.0f;
        int cantidadVenta = 0;
        for (Producto producto : productosEncontrados) {
            // Verificar si hay suficiente stock del producto
            if (producto.getCantidad() >= 1) {
                // Restar 1 a la cantidad del producto en la base de datos
                producto.setCantidad(producto.getCantidad() - 1);
                // Actualizar el precio total de la venta
                precioTotalVenta += producto.getPrecio();
                // suma la cantidad de productos
                cantidadVenta++;
            } else {
                // Informar que no hay suficiente stock para el producto
                return null;
            }
        }

        Venta sale = new Venta();
        sale.setCliente(clienteEncontrado);
        sale.setProductos(productosVendidos);
        sale.setCantidad(cantidadVenta);
        sale.setPrecioTotal(precioTotalVenta);
        // Obtener la fecha del servicio externo
        LocalDateTime fechaActual = fechaService.obtenerFecha();
        // Asignar la fecha a la venta
        sale.setFecha(fechaActual);
        return repo.save(sale);

    }

    public boolean updateVenta(Long ventaId, Venta venta) {
        Optional<Venta> optionalVenta = repo.findById(ventaId);
        boolean presente = false; // Venta no encontrado

        if (optionalVenta.isPresent()) {
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
