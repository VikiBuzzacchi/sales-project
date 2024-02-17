package com.example.demo.models;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Venta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Id de la venta generado por la DB", requiredMode = Schema.RequiredMode.AUTO, example = "1")
    private Long id;

    @Schema(description = "Fecha de la venta en la venta", requiredMode = Schema.RequiredMode.REQUIRED, example = "2024-02-07T08:30:00")
    // @Temporal(TemporalType.TIMESTAMP)
    // private Date fecha;
    private LocalDateTime fecha;

    @Column
    @Schema(description = "Cantidad de productos en la venta", requiredMode = Schema.RequiredMode.REQUIRED, example = "4")
    private int cantidad;

    @Column(name = "precio_total")
    @Schema(description = "Precio total de la venta", requiredMode = Schema.RequiredMode.REQUIRED, example = "1760.0")
    private float precioTotal;

    @ManyToMany
    @JoinTable(name = "producto_venta", joinColumns = @JoinColumn(name = "venta_id"), inverseJoinColumns = @JoinColumn(name = "producto_id"))
    private List<Producto> productos;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

}
