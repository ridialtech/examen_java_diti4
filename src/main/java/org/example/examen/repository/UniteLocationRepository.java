package org.example.examen.repository;

import org.example.examen.model.UniteLocation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UniteLocationRepository extends JpaRepository<UniteLocation, Long> {
    List<UniteLocation> findByImmeubleId(Long immeubleId);
    List<UniteLocation> findByStatut(String statut);
}

