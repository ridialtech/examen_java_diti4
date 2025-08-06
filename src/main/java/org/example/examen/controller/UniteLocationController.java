package org.example.examen.controller;

import org.example.examen.model.UniteLocation;
import org.example.examen.service.UniteLocationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/immeubles/{immeubleId}/unites")
public class UniteLocationController {

    private final UniteLocationService service;

    public UniteLocationController(UniteLocationService service) {
        this.service = service;
    }

    @GetMapping
    public List<UniteLocation> getAll(@PathVariable Long immeubleId) {
        return service.findByImmeuble(immeubleId);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UniteLocation> getById(@PathVariable Long immeubleId, @PathVariable Long id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public UniteLocation create(@PathVariable Long immeubleId, @RequestBody UniteLocation unite) {
        return service.save(immeubleId, unite);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UniteLocation> update(@PathVariable Long immeubleId, @PathVariable Long id, @RequestBody UniteLocation unite) {
        return service.findById(id)
                .map(existing -> {
                    unite.setId(id);
                    return ResponseEntity.ok(service.save(immeubleId, unite));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long immeubleId, @PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
