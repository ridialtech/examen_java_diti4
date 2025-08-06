package org.example.examen.repository;

import org.example.examen.model.Locataire;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocataireRepository extends JpaRepository<Locataire, Long> {
}

