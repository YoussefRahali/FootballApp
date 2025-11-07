package com.example.microcompetition.service;

import com.example.microcompetition.Iservice.ICompetitionService;
import com.example.microcompetition.entity.Competition;
import com.example.microcompetition.repository.CompetitionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompetitionService implements ICompetitionService {

    private final CompetitionRepository repo;

    public CompetitionService(CompetitionRepository repo) {
        this.repo = repo;
    }

    public List<Competition> getAll() {
        return repo.findAll();
    }

    public Optional<Competition> getById(String id) {
        return repo.findById(id);
    }

    public Competition add(Competition c) {
        return repo.save(c);
    }

    public Competition update(String id, Competition c) {
        c.setId(id);
        return repo.save(c);
    }

    public void delete(String id) {
        repo.deleteById(id);
    }
}
