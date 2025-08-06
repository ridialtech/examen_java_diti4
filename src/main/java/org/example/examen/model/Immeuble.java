package org.example.examen.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
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

    public Immeuble() {
    }

    public Immeuble(Long id, String nom, String adresse, String description, int nombreUnites) {
        this.id = id;
        this.nom = nom;
        this.adresse = adresse;
        this.description = description;
        this.nombreUnites = nombreUnites;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getNombreUnites() {
        return nombreUnites;
    }

    public void setNombreUnites(int nombreUnites) {
        this.nombreUnites = nombreUnites;
    }

    public List<UniteLocation> getUnites() {
        return unites;
    }

    public void setUnites(List<UniteLocation> unites) {
        this.unites = unites;
    }
}

