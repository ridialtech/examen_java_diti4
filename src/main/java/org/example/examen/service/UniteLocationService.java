package org.example.examen.service;

import org.example.examen.model.Immeuble;
import org.example.examen.model.UniteLocation;
import org.example.examen.repository.ImmeubleRepository;
import org.example.examen.repository.UniteLocationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UniteLocationService {

    private final UniteLocationRepository uniteRepository;
    private final ImmeubleRepository immeubleRepository;

    public UniteLocationService(UniteLocationRepository uniteRepository, ImmeubleRepository immeubleRepository) {
        this.uniteRepository = uniteRepository;
        this.immeubleRepository = immeubleRepository;
    }

    public List<UniteLocation> findAll() {
        return uniteRepository.findAll();
    }

    public List<UniteLocation> findByImmeuble(Long immeubleId) {
        return uniteRepository.findByImmeubleId(immeubleId);
    }

    public Optional<UniteLocation> findById(Long id) {
        return uniteRepository.findById(id);
    }

    public UniteLocation save(Long immeubleId, UniteLocation unite) {
        Immeuble immeuble = immeubleRepository.findById(immeubleId).orElseThrow();
        unite.setImmeuble(immeuble);
        return uniteRepository.save(unite);
    }

    public void delete(Long id) {
        uniteRepository.deleteById(id);
    }
}
