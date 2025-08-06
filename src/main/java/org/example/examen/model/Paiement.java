package org.example.examen.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Paiement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate datePaiement;

    private double montant;

    private String statut; // paye ou en_retard

    @ManyToOne
    @JoinColumn(name = "contrat_id")
    private Contrat contrat;

    public Paiement() {
    }

    public Paiement(Long id, LocalDate datePaiement, double montant, String statut, Contrat contrat) {
        this.id = id;
        this.datePaiement = datePaiement;
        this.montant = montant;
        this.statut = statut;
        this.contrat = contrat;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDatePaiement() {
        return datePaiement;
    }

    public void setDatePaiement(LocalDate datePaiement) {
        this.datePaiement = datePaiement;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public Contrat getContrat() {
        return contrat;
    }

    public void setContrat(Contrat contrat) {
        this.contrat = contrat;
    }
}

