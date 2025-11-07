package com.example.microcompetition.service;

import com.example.microcompetition.entity.Club;
import com.example.microcompetition.repository.ClubRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClubService {
    private final ClubRepository repo;

    public ClubService(ClubRepository repo) {
        this.repo = repo;
    }


    public List<Club> getTous() {
        return repo.findAll();
    }

    public Optional<Club> getParId(String id) {
        return repo.findById(id);
    }

    public Club ajouter(Club c) {
        return repo.save(c);
    }

    public Club mettreAJour(String id, Club c) {
        c.setId(id);
        return repo.save(c);
    }

    public void supprimer(String id) {
        repo.deleteById(id);
    }
}