package tn.esprit.transfert.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.transfert.DTO.Player;
import tn.esprit.transfert.DTO.Team;
import tn.esprit.transfert.repository.PlayerRepository;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RecommenderService {

    @Autowired
    private PlayerRepository playerRepo;

    @Autowired
    private PlayerValuationService valuation;

    @Autowired
    private CareerPredictionService careerPrediction;

    /**
     * Recommande une liste de joueurs pour une équipe selon budget, poste et potentiel.
     */
    public List<Player> recommendForTeam(Team team, int limit) {
        if (team == null || limit <= 0) return Collections.emptyList();

        // Récupérer tous les joueurs
        List<Player> allPlayers = playerRepo.findAll();
        if (allPlayers.isEmpty()) return Collections.emptyList();

        // Filtrer par budget
        List<Player> affordable = allPlayers.stream()
                .filter(p -> p != null && p.getId() != null)
                .filter(p -> team.getBudget() >= valuation.estimateValue(p))
                .collect(Collectors.toList());

        if (affordable.isEmpty()) return Collections.emptyList();

        // Trier par score pour l'équipe (compatibilité poste + potentiel + prix)
        return affordable.stream()
                .sorted(Comparator.comparingDouble(p -> -scoreForTeam(team, p)))
                .limit(limit)
                .collect(Collectors.toList());
    }

    /**
     * Score d’un joueur pour une équipe.
     */
    private double scoreForTeam(Team team, Player p) {
        double comp = positionCompatibility(team, p);       // compatibilité poste
        double potential = potentialScore(p);              // potentiel futur
        double priceSuitability = Math.max(0, 1 - valuation.estimateValue(p) / team.getBudget());

        return comp * 0.5 + potential * 0.3 + priceSuitability * 0.2;
    }

    private double positionCompatibility(Team team, Player p) {
        if (team.getSquad() == null || p == null || p.getPosition() == null) return 0.5;
        // Exemple simple : si l'équipe a moins de joueurs à ce poste, compatibilité = 1
        long count = team.getSquad().stream()
                .filter(pl -> pl.getPosition() != null && pl.getPosition().equalsIgnoreCase(p.getPosition()))
                .count();
        return count < 2 ? 1.0 : 0.7; // ajustable
    }

    private double potentialScore(Player p) {
        if (p == null) return 0.5;
        // Utiliser CareerPredictionService pour estimer potentiel sur 5 ans
        List<Double> future = careerPrediction.predictFutureSkillCurve(p, 5);
        return future.stream().mapToDouble(Double::doubleValue).average().orElse(50) / 100.0; // normalisé 0-1
    }
}
