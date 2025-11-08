package com.example.microcompetition.Iservice;

import com.example.microcompetition.dto.ClubDTO;
import com.example.microcompetition.entity.Competition;
import com.example.microcompetition.entity.TypeCompetition;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ICompetitionService {

    List<Competition> getAll();

    Optional<Competition> getById(String id);

    Competition add(Competition c);

    Competition update(String id, Competition c);

    void delete(String id);

    // --- RECHERCHE ET FILTRAGE SIMPLES ---
    List<Competition> searchByName(String nom);

    List<Competition> filterByType(TypeCompetition type);

    List<Competition> filterBySaison(String saison);

    List<Competition> filterByPeriod(LocalDate start, LocalDate end);

    Competition assignClub(String competitionId, String clubId);
    Competition removeClub(String competitionId, String clubId);
    List<ClubDTO> getClubsForCompetition(String competitionId);
}
