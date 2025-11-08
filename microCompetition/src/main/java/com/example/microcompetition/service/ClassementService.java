package com.example.microcompetition.service;

import com.example.microcompetition.Iservice.IClassementService;
import com.example.microcompetition.client.ClubClient;
import com.example.microcompetition.client.MatchClient;
import com.example.microcompetition.dto.ClubDTO;
import com.example.microcompetition.dto.matchdto;
import com.example.microcompetition.entity.Classement;
import com.example.microcompetition.repository.ClassementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ClassementService implements IClassementService {

    private final ClassementRepository repository;
    private final MatchClient matchClient;

    public ClassementService(ClassementRepository repository, ClubClient clubClient, MatchClient matchClient) {
        this.repository = repository;
        this.clubClient = clubClient;
        this.matchClient = matchClient;
    }

    // --- GET ALL ---
    @Override
    public List<Classement> getAllClassements() {
        return repository.findAll();
    }

    // --- GET BY ID ---
    @Override
    public Classement getClassementById(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Classement non trouvé"));
    }

    // --- CREATE ---
    @Override
    public Classement saveClassement(Classement classement) {
        // Vérifier le club via ClubClient
        ClubDTO club = clubClient.getClubById(classement.getClubId());
        if (club == null) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Club introuvable");

        // Vérifier le match via MatchClient

        // Calcul des points
        classement.calculerPoints();
        return repository.save(classement);
    }

    // --- UPDATE ---
    @Override
    public Classement updateClassement(String id, Classement updated) {
        return repository.findById(id)
                .map(c -> {
                    // Vérifier le club
                    ClubDTO club = clubClient.getClubById(updated.getClubId());
                    if (club == null) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Club introuvable");

                    // Vérifier le match


                    c.setClubId(updated.getClubId());
                    c.setMatchsJoues(updated.getMatchsJoues());
                    c.setVictoires(updated.getVictoires());
                    c.setNuls(updated.getNuls());
                    c.setDefaites(updated.getDefaites());

                    c.calculerPoints();
                    return repository.save(c);
                })
                .orElseGet(() -> {
                    updated.calculerPoints();
                    updated.setId(id);
                    return repository.save(updated);
                });
    }

    // --- DELETE ---
    @Override
    public void deleteClassement(String id) {
        if (!repository.existsById(id))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Classement non trouvé");
        repository.deleteById(id);
    }

    // --- GET DETAILS CLUB + MATCH ---
    @Override
    public Map<String, Object> getClassementDetails(String id) {
        Classement c = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Classement introuvable"));

        Map<String, Object> details = new HashMap<>();
        details.put("classement", c);
        details.put("club", clubClient.getClubById(c.getClubId()));

        return details;
    }

    @Autowired
    private ClubClient clubClient;

    public List<ClubDTO> getAllClubs(){
        return clubClient.getAllClubs();
    }

    public ClubDTO getClubById(@PathVariable("id") String id){
        return clubClient.getClubById(id);
    }

}
