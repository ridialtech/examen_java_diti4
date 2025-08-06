package org.example.examen.controller;

import org.example.examen.model.Locataire;
import org.example.examen.service.LocataireService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/locataires")
public class LocataireController {

    private final LocataireService service;

    public LocataireController(LocataireService service) {
        this.service = service;
    }

    @GetMapping
    public List<Locataire> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Locataire> getById(@PathVariable Long id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Locataire create(@RequestBody Locataire locataire) {
        return service.save(locataire);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Locataire> update(@PathVariable Long id, @RequestBody Locataire locataire) {
        return service.findById(id)
                .map(existing -> {
                    locataire.setId(id);
                    return ResponseEntity.ok(service.save(locataire));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
