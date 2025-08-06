package org.example.examen.service;

import org.example.examen.model.Locataire;
import org.example.examen.repository.LocataireRepository;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LocataireService {

    private final LocataireRepository repository;

    public List<Locataire> findAll() {
        return repository.findAll();
    }

    public Optional<Locataire> findById(Long id) {
        return repository.findById(id);
    }

    public Locataire save(Locataire locataire) {
        return repository.save(locataire);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
