package org.example.examen.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Immeuble {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;

    private String adresse;

    private String description;

    private int nombreUnites;

    @OneToMany(mappedBy = "immeuble", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UniteLocation> unites = new ArrayList<>();
}

