package org.example.examen.service;

import lombok.RequiredArgsConstructor;
import org.example.examen.model.Paiement;
import org.example.examen.repository.PaiementRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PaiementService {
    private final PaiementRepository repository;

    public List<Paiement> findAll() {
        return repository.findAll();
    }

    public Optional<Paiement> findById(Long id) {
        return repository.findById(id);
    }

    public Paiement save(Paiement paiement) {
        return repository.save(paiement);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
