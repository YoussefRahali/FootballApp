package com.example.microjoueur;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JoueurRepository extends MongoRepository<Joueur, String> {

    // Recherche par nom (insensible à la casse, partielle)
    List<Joueur> findByNomContainingIgnoreCase(String nom);

    // Recherche par prénom (insensible à la casse, partielle)
    List<Joueur> findByPrenomContainingIgnoreCase(String prenom);

    // Recherche par nationalité (insensible à la casse, partielle)
    List<Joueur> findByNationaliteContainingIgnoreCase(String nationalite);

    // Recherche par position (exact, insensible à la casse)
    List<Joueur> findByPositionIgnoreCase(String position);

    // Recherche combinée avec Query personnalisée
    @Query("{ $and: [ " +
           "{ $or: [ { 'nom': { $regex: ?0, $options: 'i' } }, { ?0: null } ] }, " +
           "{ $or: [ { 'prenom': { $regex: ?1, $options: 'i' } }, { ?1: null } ] }, " +
           "{ $or: [ { 'nationalite': { $regex: ?2, $options: 'i' } }, { ?2: null } ] }, " +
           "{ $or: [ { 'position': { $regex: ?3, $options: 'i' } }, { ?3: null } ] } " +
           "] }")
    List<Joueur> searchJoueurs(String nom, String prenom, String nationalite, String position);

    // Récupérer tous les joueurs avec tri
    List<Joueur> findAll(Sort sort);
}
