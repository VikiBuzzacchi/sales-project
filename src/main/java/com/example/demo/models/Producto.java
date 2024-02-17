package com.example.demo.models;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Id del producto generado por la DB", requiredMode = Schema.RequiredMode.AUTO, example = "1")
    private Long id;

    @Column
    @Schema(description = "Nombre del producto", requiredMode = Schema.RequiredMode.REQUIRED, example = "Computadora")
    private String nombre;

    @Column
    @Schema(description = "Cantidad de productos del mismo tipo", requiredMode = Schema.RequiredMode.REQUIRED, example = "10")
    private Integer cantidad;

    @Column
    @Schema(description = "Precio del producto", requiredMode = Schema.RequiredMode.REQUIRED, example = "950.5")
    private Double precio;

    @Column
    @Schema(description = "Código del producto", requiredMode = Schema.RequiredMode.REQUIRED, example = "5050")
    private Integer codigo;

    @Column
    @Schema(description = "Descripción del producto", requiredMode = Schema.RequiredMode.REQUIRED, example = "Dispositivo electrónico que procesa datos y ejecuta programas.")
    private String descripcion;

    // @ManyToOne(optional = false)
    // @JoinColumn(name = "venta_id")
    // private Venta venta;

    // @ManyToMany(mappedBy = "productos")
    // private List<Venta> ventas;

}