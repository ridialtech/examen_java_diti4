package org.example.examen.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Locataire {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;

    private String email;

    private String motDePasse;

    @OneToMany(mappedBy = "locataire", cascade = CascadeType.ALL)
    private List<Contrat> contrats = new ArrayList<>();
}

