package org.example.examen.model;

import jakarta.persistence.*;
import java.time.LocalDate;
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
public class Contrat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate dateDebut;

    private LocalDate dateFin;

    private double montant;

    @ManyToOne
    @JoinColumn(name = "unite_id")
    private UniteLocation uniteLocation;

    @ManyToOne
    @JoinColumn(name = "locataire_id")
    private Locataire locataire;

    @OneToMany(mappedBy = "contrat", cascade = CascadeType.ALL)
    private List<Paiement> paiements = new ArrayList<>();
}

