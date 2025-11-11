package com.example.microlocal;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDateTime;

public class MatchDTO {
  private String id;

  @JsonProperty("team1Id")
  private String idClubDomicile;

  @JsonProperty("team2Id")
  private String idClubExterieur;

  @JsonProperty("localId")
  private String stade;

  @JsonProperty("date")
  private LocalDateTime dateMatch;

  @JsonProperty("status")
  private String statut;

  public MatchDTO() {
  }

  public MatchDTO(String id, String idClubDomicile, String idClubExterieur,
                  String stade, LocalDateTime dateMatch, String statut) {
    this.id = id;
    this.idClubDomicile = idClubDomicile;
    this.idClubExterieur = idClubExterieur;
    this.stade = stade;
    this.dateMatch = dateMatch;
    this.statut = statut;
  }

  // ====== Getters ======
  public String getId() {
    return id;
  }

  public String getIdClubDomicile() {
    return idClubDomicile;
  }

  public String getIdClubExterieur() {
    return idClubExterieur;
  }

  public String getStade() {
    return stade;
  }

  public LocalDateTime getDateMatch() {
    return dateMatch;
  }

  public String getStatut() {
    return statut;
  }

  // ====== Setters ======
  public void setId(String id) {
    this.id = id;
  }

  public void setIdClubDomicile(String idClubDomicile) {
    this.idClubDomicile = idClubDomicile;
  }

  public void setIdClubExterieur(String idClubExterieur) {
    this.idClubExterieur = idClubExterieur;
  }

  public void setStade(String stade) {
    this.stade = stade;
  }

  public void setDateMatch(LocalDateTime dateMatch) {
    this.dateMatch = dateMatch;
  }

  public void setStatut(String statut) {
    this.statut = statut;
  }

  @Override
  public String toString() {
    return "MatchDTO{" +
            "id='" + id + '\'' +
            ", idClubDomicile='" + idClubDomicile + '\'' +
            ", idClubExterieur='" + idClubExterieur + '\'' +
            ", stade='" + stade + '\'' +
            ", dateMatch=" + dateMatch +
            ", statut='" + statut + '\'' +
            '}';
  }
}
