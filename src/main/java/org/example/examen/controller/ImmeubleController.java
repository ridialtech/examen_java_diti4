package org.example.examen.controller;

import org.example.examen.model.Immeuble;
import org.example.examen.service.ImmeubleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/immeubles")
public class ImmeubleController {

    private final ImmeubleService service;

    public ImmeubleController(ImmeubleService service) {
        this.service = service;
    }

    @GetMapping
    public List<Immeuble> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Immeuble> getById(@PathVariable Long id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Immeuble create(@RequestBody Immeuble immeuble) {
        return service.save(immeuble);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Immeuble> update(@PathVariable Long id, @RequestBody Immeuble immeuble) {
        return service.findById(id)
                .map(existing -> {
                    immeuble.setId(id);
                    return ResponseEntity.ok(service.save(immeuble));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}

