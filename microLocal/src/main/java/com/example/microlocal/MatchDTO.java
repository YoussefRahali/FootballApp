package com.example.microlocal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class MatchDTO {
  private String id;
  private String idCompetition;
  private String idClubDomicile;
  private String idClubExterieur;
  private int butsDomicile;
  private int butsExterieur;
  private LocalDate dateMatch;
  private String statut;
  private String vainqueur;
  private String stade;
  private boolean domicile;
  private String tour;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getIdCompetition() {
    return idCompetition;
  }

  public void setIdCompetition(String idCompetition) {
    this.idCompetition = idCompetition;
  }

  public String getIdClubDomicile() {
    return idClubDomicile;
  }

  public void setIdClubDomicile(String idClubDomicile) {
    this.idClubDomicile = idClubDomicile;
  }

  public String getIdClubExterieur() {
    return idClubExterieur;
  }

  public void setIdClubExterieur(String idClubExterieur) {
    this.idClubExterieur = idClubExterieur;
  }

  public int getButsDomicile() {
    return butsDomicile;
  }

  public void setButsDomicile(int butsDomicile) {
    this.butsDomicile = butsDomicile;
  }

  public int getButsExterieur() {
    return butsExterieur;
  }

  public void setButsExterieur(int butsExterieur) {
    this.butsExterieur = butsExterieur;
  }

  public LocalDate getDateMatch() {
    return dateMatch;
  }

  public void setDateMatch(LocalDate dateMatch) {
    this.dateMatch = dateMatch;
  }

  public String getStatut() {
    return statut;
  }

  public void setStatut(String statut) {
    this.statut = statut;
  }

  public String getVainqueur() {
    return vainqueur;
  }

  public void setVainqueur(String vainqueur) {
    this.vainqueur = vainqueur;
  }

  public String getStade() {
    return stade;
  }

  public void setStade(String stade) {
    this.stade = stade;
  }

  public boolean isDomicile() {
    return domicile;
  }

  public void setDomicile(boolean domicile) {
    this.domicile = domicile;
  }

  public String getTour() {
    return tour;
  }

  public void setTour(String tour) {
    this.tour = tour;
  }
}
