package org.example.examen.service;

import org.example.examen.model.Immeuble;
import org.example.examen.repository.ImmeubleRepository;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ImmeubleService {

    private final ImmeubleRepository repository;

    public List<Immeuble> findAll() {
        return repository.findAll();
    }

    public Optional<Immeuble> findById(Long id) {
        return repository.findById(id);
    }

    public Immeuble save(Immeuble immeuble) {
        return repository.save(immeuble);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}

