package com.example.microjoueur;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/joueurs")
@CrossOrigin
public class JoueurRestAPI {
    private final JoueurService service;

    @Autowired
    public JoueurRestAPI(JoueurService service) {
        this.service = service;
    }

    @GetMapping
    public List<Joueur> all() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Joueur> one(@PathVariable String id) {
        Joueur j = service.getById(id);
        return (j == null) ? ResponseEntity.notFound().build() : ResponseEntity.ok(j);
    }

    @PostMapping
    public Joueur create(@RequestBody Joueur j) {
        return service.create(j);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Joueur> update(@PathVariable String id, @RequestBody Joueur j) {
        Joueur updated = service.update(id, j);
        return (updated == null) ? ResponseEntity.notFound().build() : ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
