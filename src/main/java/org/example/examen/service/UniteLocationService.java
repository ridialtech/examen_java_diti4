package org.example.examen.service;

import org.example.examen.model.UniteLocation;
import org.example.examen.repository.UniteLocationRepository;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UniteLocationService {

    private final UniteLocationRepository repository;

    public List<UniteLocation> findAll() {
        return repository.findAll();
    }

    public Optional<UniteLocation> findById(Long id) {
        return repository.findById(id);
    }

    public UniteLocation save(UniteLocation unite) {
        return repository.save(unite);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
