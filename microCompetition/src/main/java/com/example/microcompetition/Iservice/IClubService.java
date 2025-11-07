package com.example.microcompetition.Iservice;

import com.example.microcompetition.entity.Club;

import java.util.List;
import java.util.Optional;

public interface IClubService {

    List<Club> getTous();

    Optional<Club> getParId(String id);

    Club ajouter(Club c);

    Club mettreAJour(String id, Club c);

    void supprimer(String id);
}
