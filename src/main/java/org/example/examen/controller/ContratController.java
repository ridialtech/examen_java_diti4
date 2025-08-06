package org.example.examen.controller;

import lombok.RequiredArgsConstructor;
import org.example.examen.model.Contrat;
import org.example.examen.service.ContratService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contrats")
@RequiredArgsConstructor
public class ContratController {

    private final ContratService service;

    @GetMapping
    public List<Contrat> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Contrat> getById(@PathVariable Long id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Contrat create(@RequestBody Contrat contrat) {
        return service.save(contrat);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Contrat> update(@PathVariable Long id, @RequestBody Contrat contrat) {
        return service.findById(id)
                .map(existing -> {
                    contrat.setId(id);
                    return ResponseEntity.ok(service.save(contrat));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
