package org.example.examen.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
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

    public Contrat() {
    }

    public Contrat(Long id, LocalDate dateDebut, LocalDate dateFin, double montant, UniteLocation uniteLocation, Locataire locataire) {
        this.id = id;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.montant = montant;
        this.uniteLocation = uniteLocation;
        this.locataire = locataire;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(LocalDate dateDebut) {
        this.dateDebut = dateDebut;
    }

    public LocalDate getDateFin() {
        return dateFin;
    }

    public void setDateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public UniteLocation getUniteLocation() {
        return uniteLocation;
    }

    public void setUniteLocation(UniteLocation uniteLocation) {
        this.uniteLocation = uniteLocation;
    }

    public Locataire getLocataire() {
        return locataire;
    }

    public void setLocataire(Locataire locataire) {
        this.locataire = locataire;
    }

    public List<Paiement> getPaiements() {
        return paiements;
    }

    public void setPaiements(List<Paiement> paiements) {
        this.paiements = paiements;
    }
}

