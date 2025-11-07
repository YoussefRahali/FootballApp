package com.example.microjoueur;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JoueurService {
    private final JoueurRepository repository;

    public JoueurService(JoueurRepository repository) {
        this.repository = repository;
    }

    public List<Joueur> getAll() {
        return repository.findAll();
    }

    public Joueur getById(String id) {
        return repository.findById(id).orElse(null);
    }

    public Joueur create(Joueur j) {
        return repository.save(j);
    }

    public void delete(String id) {
        repository.deleteById(id);
    }

    public Joueur update(String id, Joueur payload) {
        return repository.findById(id).map(j -> {
            j.setNom(payload.getNom());
            j.setPrenom(payload.getPrenom());
            j.setAge(payload.getAge());
            j.setPoste(payload.getPoste());
            j.setNumero(payload.getNumero());
            j.setClub(payload.getClub());
            j.setNationalite(payload.getNationalite());
            return repository.save(j);
        }).orElse(null);
    }
}
