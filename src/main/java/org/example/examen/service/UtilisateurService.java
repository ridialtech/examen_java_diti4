package org.example.examen.service;

import lombok.RequiredArgsConstructor;
import org.example.examen.model.Utilisateur;
import org.example.examen.repository.UtilisateurRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UtilisateurService {
    private final UtilisateurRepository repository;

    public List<Utilisateur> findAll() {
        return repository.findAll();
    }

    public Optional<Utilisateur> findById(Long id) {
        return repository.findById(id);
    }

    public Utilisateur save(Utilisateur utilisateur) {
        return repository.save(utilisateur);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
