package org.example.examen.service;

import lombok.RequiredArgsConstructor;
import org.example.examen.model.Contrat;
import org.example.examen.repository.ContratRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ContratService {
    private final ContratRepository repository;

    public List<Contrat> findAll() {
        return repository.findAll();
    }

    public Optional<Contrat> findById(Long id) {
        return repository.findById(id);
    }

    public Contrat save(Contrat contrat) {
        return repository.save(contrat);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
