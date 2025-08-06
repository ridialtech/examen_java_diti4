package org.example.examen.controller;

import lombok.RequiredArgsConstructor;
import org.example.examen.model.Paiement;
import org.example.examen.service.PaiementService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/paiements")
@RequiredArgsConstructor
public class PaiementController {

    private final PaiementService service;

    @GetMapping
    public List<Paiement> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Paiement> getById(@PathVariable Long id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Paiement create(@RequestBody Paiement paiement) {
        return service.save(paiement);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Paiement> update(@PathVariable Long id, @RequestBody Paiement paiement) {
        return service.findById(id)
                .map(existing -> {
                    paiement.setId(id);
                    return ResponseEntity.ok(service.save(paiement));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
