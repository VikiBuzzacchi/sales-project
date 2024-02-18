package com.example.demo.models;

import java.time.LocalDateTime;
import java.util.List;

public class VentaRequest {
    private Long clienteId;
    private List<Long> productoIds;
    private int cantidad;
    private float total;
    private LocalDateTime fecha;

    public VentaRequest(Long clienteId, List<Long> productoIds, int cantidad, float total) {
        this.clienteId = clienteId;
        this.productoIds = productoIds;
        this.cantidad = cantidad;
        this.total = total;
    }

    public Long getClienteId() {
        return clienteId;
    }

    public List<Long> getProductoIds() {
        return productoIds;
    }

    public int getCantidad() {
        return cantidad;
    }

    public float getTotal() {
        return total;
    }

    public void setFecha(LocalDateTime fechaActual) {
        this.fecha = fechaActual;
    }

}
