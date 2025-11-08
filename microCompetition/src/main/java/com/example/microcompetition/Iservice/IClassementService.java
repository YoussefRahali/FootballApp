package com.example.microcompetition.Iservice;

import com.example.microcompetition.dto.ClubDTO;
import com.example.microcompetition.entity.Classement;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Map;

public interface IClassementService {

    List<Classement> getAllClassements();

    Classement getClassementById(String id);

    Classement saveClassement(Classement classement);

    Classement updateClassement(String id, Classement classement);

    void deleteClassement(String id);

    // --- Nouvelle méthode pour récupérer les détails club + match ---
    Map<String, Object> getClassementDetails(String id);

    public List<ClubDTO> getAllClubs();
    public ClubDTO getClubById( String id);

}
