package com.example.microcompetition.dto;

import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

import java.time.LocalDate;

public class matchdto {
    private String id;
    private String idCompetition;
    private String idClubDomicile;
    private String idClubExterieur;
    private int butsDomicile;
    private int butsExterieur;
    private LocalDate dateMatch;

    @Field(targetType = FieldType.STRING)
    private StatutMatch statut;

    @Field(targetType = FieldType.STRING)
    private VainqueurMatch vainqueur;

    private String stade;
    private boolean domicile;

    @Field(targetType = FieldType.STRING)
    private TourMatch tour;

    // ✅ Getters et Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getIdCompetition() { return idCompetition; }
    public void setIdCompetition(String idCompetition) { this.idCompetition = idCompetition; }

    public String getIdClubDomicile() { return idClubDomicile; }
    public void setIdClubDomicile(String idClubDomicile) { this.idClubDomicile = idClubDomicile; }

    public String getIdClubExterieur() { return idClubExterieur; }
    public void setIdClubExterieur(String idClubExterieur) { this.idClubExterieur = idClubExterieur; }

    public int getButsDomicile() { return butsDomicile; }
    public void setButsDomicile(int butsDomicile) { this.butsDomicile = butsDomicile; }

    public int getButsExterieur() { return butsExterieur; }
    public void setButsExterieur(int butsExterieur) { this.butsExterieur = butsExterieur; }

    public LocalDate getDateMatch() { return dateMatch; }
    public void setDateMatch(LocalDate dateMatch) { this.dateMatch = dateMatch; }

    public StatutMatch getStatut() { return statut; }
    public void setStatut(StatutMatch statut) { this.statut = statut; }

    public VainqueurMatch getVainqueur() { return vainqueur; }
    public void setVainqueur(VainqueurMatch vainqueur) { this.vainqueur = vainqueur; }

    public String getStade() { return stade; }
    public void setStade(String stade) { this.stade = stade; }

    public boolean isDomicile() { return domicile; }
    public void setDomicile(boolean domicile) { this.domicile = domicile; }

    public TourMatch getTour() { return tour; }
    public void setTour(TourMatch tour) { this.tour = tour; }
}
