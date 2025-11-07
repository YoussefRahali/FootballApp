package com.example.microbillet;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


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
}
