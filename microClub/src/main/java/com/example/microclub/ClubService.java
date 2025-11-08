package com.example.microclub;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class ClubService {
    private final ClubRepository clubRepository;

    public ClubService(ClubRepository clubRepository) {
        this.clubRepository = clubRepository;
    }

    public List<Club> getAllClubs() {
        return clubRepository.findAll();
    }

    public Optional<Club> getClubById(String id) {
        return clubRepository.findById(id);
    }

    public Club createClub(Club club) {
        return clubRepository.save(club);
    }

    public Club updateClub(String id, Club clubDetails) {
        return clubRepository.findById(id).map(club -> {
            club.setName(clubDetails.getName());
            club.setCity(clubDetails.getCity());
            club.setEstablishedYear(clubDetails.getEstablishedYear());
            club.setPresident(clubDetails.getPresident());
            club.setBudget(clubDetails.getBudget());
            return clubRepository.save(club);
        }).orElseThrow(() -> new RuntimeException("Club not found"));
    }

    public void deleteClub(String id) {
        clubRepository.deleteById(id);
    }

    @Autowired
    private JoueurClient joueurClient;

    public List<Joueur> all(){
        return joueurClient.all();
    }


    public Joueur one(String id){
        return joueurClient.one(id);
    }

    // Filter clubs by name
    public List<Club> findByName(String name) {
        return clubRepository.findByNameContainingIgnoreCase(name);
    }

    // Filter clubs by president
    public List<Club> findByPresident(String president) {
        return clubRepository.findByPresidentContainingIgnoreCase(president);
    }

    // Advanced: Get players by club name
    public List<Joueur> getPlayersByClubName(String clubName) {
        List<Joueur> allJoueurs = joueurClient.all();
        return allJoueurs.stream()
                .filter(joueur -> joueur.getClub() != null && joueur.getClub().equalsIgnoreCase(clubName))
                .toList();
    }

    // Advanced: Count players in a club
    public int countPlayersByClubName(String clubName) {
        return getPlayersByClubName(clubName).size();
    }

    // Advanced: Generate suggested formation for a club
    public FormationDTO getSuggestedFormation(String clubName) {
        List<Joueur> allPlayers = getPlayersByClubName(clubName);

        // Séparer les joueurs par position
        List<Joueur> goalkeepers = allPlayers.stream()
                .filter(j -> j.getPoste() != null && j.getPoste().equalsIgnoreCase("GK"))
                .limit(1)
                .toList();

        List<Joueur> defenders = allPlayers.stream()
                .filter(j -> j.getPoste() != null && j.getPoste().equalsIgnoreCase("DEF"))
                .limit(4)
                .toList();

        List<Joueur> midfielders = allPlayers.stream()
                .filter(j -> j.getPoste() != null && j.getPoste().equalsIgnoreCase("MID"))
                .limit(3)
                .toList();

        List<Joueur> attackers = allPlayers.stream()
                .filter(j -> j.getPoste() != null && j.getPoste().equalsIgnoreCase("ATT"))
                .limit(3)
                .toList();

        // Les joueurs restants deviennent remplaçants
        List<Joueur> starters = new java.util.ArrayList<>();
        starters.addAll(goalkeepers);
        starters.addAll(defenders);
        starters.addAll(midfielders);
        starters.addAll(attackers);

        List<Joueur> substitutes = allPlayers.stream()
                .filter(j -> !starters.contains(j))
                .toList();

        // Déterminer le nom de la formation
        String formationName = String.format("%d-%d-%d",
                defenders.size(),
                midfielders.size(),
                attackers.size());

        String description = String.format("Formation tactique avec %d défenseurs, %d milieux et %d attaquants",
                defenders.size(), midfielders.size(), attackers.size());

        return new FormationDTO(formationName, description, goalkeepers, defenders,
                               midfielders, attackers, substitutes);
    }
}
