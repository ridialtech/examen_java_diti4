package org.example.examen.controller;

import lombok.RequiredArgsConstructor;
import org.example.examen.model.Utilisateur;
import org.example.examen.service.UtilisateurService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/utilisateurs")
@RequiredArgsConstructor
public class UtilisateurController {

    private final UtilisateurService service;

    @GetMapping
    public List<Utilisateur> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Utilisateur> getById(@PathVariable Long id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Utilisateur create(@RequestBody Utilisateur utilisateur) {
        return service.save(utilisateur);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Utilisateur> update(@PathVariable Long id, @RequestBody Utilisateur utilisateur) {
        return service.findById(id)
                .map(existing -> {
                    utilisateur.setId(id);
                    return ResponseEntity.ok(service.save(utilisateur));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
