package org.example.examen.repository;

import org.example.examen.model.Immeuble;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImmeubleRepository extends JpaRepository<Immeuble, Long> {
}

