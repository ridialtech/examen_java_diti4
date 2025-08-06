package org.example.examen.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UniteLocation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String numero;

    private double surface;

    private int pieces;

    private double loyer;

    private String statut; // disponible ou occupe

    @ManyToOne
    @JoinColumn(name = "immeuble_id")
    private Immeuble immeuble;
}

