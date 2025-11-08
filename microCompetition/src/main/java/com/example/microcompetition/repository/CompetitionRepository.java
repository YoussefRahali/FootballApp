package com.example.microcompetition.repository;

import com.example.microcompetition.entity.Competition;
import com.example.microcompetition.entity.TypeCompetition;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDate;
import java.util.List;

public interface CompetitionRepository extends MongoRepository<Competition, String> {

    // Chercher par nom contenant une chaîne (recherche simple)
    List<Competition> findByNomContainingIgnoreCase(String nom);

    // Chercher par type
    List<Competition> findByType(TypeCompetition type);

    // Chercher par saison
    List<Competition> findBySaison(String saison);

    // Filtrer par date de début entre 2 dates
    List<Competition> findByDateDebutBetween(LocalDate start, LocalDate end);
}
