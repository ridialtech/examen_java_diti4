package org.example.examen.repository;

import org.example.examen.model.Locataire;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LocataireRepository extends JpaRepository<Locataire, Long> {
    Optional<Locataire> findByEmailAndMotDePasse(String email, String motDePasse);
}

