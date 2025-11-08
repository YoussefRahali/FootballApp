package com.example.microcompetition.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Document(collection = "competitions")
public class Competition {

    @Id
    private String id;
    private String nom;
    private TypeCompetition type;
    private String saison;
    private LocalDate dateDebut;
    private LocalDate dateFin;

    // Store only club IDs
    private List<String> clubIds = new ArrayList<>();

    // --- Constructors ---
    public Competition() {}

    public Competition(String id, String nom, TypeCompetition type, String saison,
                       LocalDate dateDebut, LocalDate dateFin) {
        this.id = id;
        this.nom = nom;
        this.type = type;
        this.saison = saison;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
    }

    // --- Getters & Setters ---
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public TypeCompetition getType() { return type; }
    public void setType(TypeCompetition type) { this.type = type; }

    public String getSaison() { return saison; }
    public void setSaison(String saison) { this.saison = saison; }

    public LocalDate getDateDebut() { return dateDebut; }
    public void setDateDebut(LocalDate dateDebut) { this.dateDebut = dateDebut; }

    public LocalDate getDateFin() { return dateFin; }
    public void setDateFin(LocalDate dateFin) { this.dateFin = dateFin; }

    public List<String> getClubIds() { return clubIds; }
    public void setClubIds(List<String> clubIds) { this.clubIds = clubIds; }

    public void addClub(String clubId) {
        if (!clubIds.contains(clubId)) {
            clubIds.add(clubId);
        }
    }

    public void removeClub(String clubId) {
        clubIds.remove(clubId);
    }
}
