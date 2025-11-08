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
}
