package com.example.demo.models;

import java.util.List;

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
    private Long id;

    @Column
    private String nombre;

    @Column
    private Integer cantidad;

    @Column
    private Double precio;

    @Column
    private Integer codigo;

    @Column
    private String descripcion;

    // @ManyToOne(optional = false)
    // @JoinColumn(name = "venta_id")
    // private Venta venta;

    @ManyToMany(mappedBy = "productos")
    private List<Venta> ventas;

}