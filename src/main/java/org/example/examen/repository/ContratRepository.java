package org.example.examen.repository;

import org.example.examen.model.Contrat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContratRepository extends JpaRepository<Contrat, Long> {
    List<Contrat> findByLocataireId(Long locataireId);
}

