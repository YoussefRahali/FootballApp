package com.example.microjoueur;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JoueurRepository extends MongoRepository<Joueur, String> {
    List<Joueur> findByClub(String club);
    List<Joueur> findByPoste(String poste);
}
