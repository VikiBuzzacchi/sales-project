package com.example.demo.models;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Id del usuario generado por la DB", requiredMode = Schema.RequiredMode.AUTO, example = "1")
    private Long id;

    @Column
    @Schema(description = "DNI del usuario", requiredMode = Schema.RequiredMode.REQUIRED, example = "43475447")
    private Integer dni;

    @Column
    @Schema(description = "Nombre del usuario", requiredMode = Schema.RequiredMode.REQUIRED, example = "Victoria")
    private String nombre;

    @Column
    @Schema(description = "Apellido del usuario", requiredMode = Schema.RequiredMode.REQUIRED, example = "Buzzacchi")
    private String apellido;

    @Column(name = "correo")
    @Schema(description = "Email del usuario", requiredMode = Schema.RequiredMode.REQUIRED, example = "vbuzzacchi7@gmail.com")
    private String email;

    // @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    // private List<Venta> ventas;

}
