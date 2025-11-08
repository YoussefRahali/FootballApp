package com.example.microcompetition.service;

import com.example.microcompetition.client.ClubClient;
import com.example.microcompetition.dto.ClubDTO;
import com.example.microcompetition.entity.Competition;
import com.example.microcompetition.entity.TypeCompetition;
import com.example.microcompetition.repository.CompetitionRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class CompetitionService {

    private final CompetitionRepository repo;
    private  ClubClient clubClient; // instance injectée

    public CompetitionService(CompetitionRepository repo, ClubClient clubClient) {
        this.repo = repo;
        this.clubClient = clubClient; // <-- initialisé

    }

    public List<Competition> getAll() {
        return repo.findAll();
    }

    public Competition getById(String id) {
        return repo.findById(id).orElse(null);
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

    // --- FILTRAGES SIMPLES ---
    public List<Competition> searchByName(String nom) {
        return repo.findByNomContainingIgnoreCase(nom);
    }

    public List<Competition> filterByType(TypeCompetition type) {
        return repo.findByType(type);
    }

    public List<Competition> filterBySaison(String saison) {
        return repo.findBySaison(saison);
    }

    public List<Competition> filterByPeriod(LocalDate start, LocalDate end) {
        return repo.findByDateDebutBetween(start, end);
    }

    public Competition assignClub(String competitionId, String clubId) {
        Competition competition = repo.findById(competitionId).orElseThrow(() -> new RuntimeException("Competition not found"));
        competition.addClub(clubId);
        return repo.save(competition);
    }

    public Competition removeClub(String competitionId, String clubId) {
        Competition competition = repo.findById(competitionId).orElseThrow(() -> new RuntimeException("Competition not found"));
        competition.removeClub(clubId);
        return repo.save(competition);
    }

    public List<ClubDTO> getClubsForCompetition(String competitionId) {
        Competition competition = repo.findById(competitionId)
                .orElseThrow(() -> new RuntimeException("Competition not found"));

        // Utilise l'instance clubClient
        return competition.getClubIds().stream()
                .map(id -> clubClient.getClubById(id)) // ✅ Correct
                .toList();
    }
}
