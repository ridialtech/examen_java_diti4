package org.example.examen.model;

import jakarta.persistence.*;
@Entity
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

    public UniteLocation() {
    }

    public UniteLocation(Long id, String numero, double surface, int pieces, double loyer, String statut, Immeuble immeuble) {
        this.id = id;
        this.numero = numero;
        this.surface = surface;
        this.pieces = pieces;
        this.loyer = loyer;
        this.statut = statut;
        this.immeuble = immeuble;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public double getSurface() {
        return surface;
    }

    public void setSurface(double surface) {
        this.surface = surface;
    }

    public int getPieces() {
        return pieces;
    }

    public void setPieces(int pieces) {
        this.pieces = pieces;
    }

    public double getLoyer() {
        return loyer;
    }

    public void setLoyer(double loyer) {
        this.loyer = loyer;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public Immeuble getImmeuble() {
        return immeuble;
    }

    public void setImmeuble(Immeuble immeuble) {
        this.immeuble = immeuble;
    }
}

