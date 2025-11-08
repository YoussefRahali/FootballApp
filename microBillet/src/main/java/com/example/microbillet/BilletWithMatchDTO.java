package com.example.microbillet;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BilletWithMatchDTO {
  private String id;
  private String numero;
  private double prix;
  private ClubDTO  idClubDomicile;
  private ClubDTO  idClubExterieur;

  private MatchDTO match;

  public BilletWithMatchDTO(Billet billet, MatchDTO match, ClubDTO e1, ClubDTO e2) {
    this.id = billet.getId();
    this.numero = billet.getNumero();
    this.prix = billet.getPrix();
    this.match = match;
    this.idClubDomicile=e1;
    this.idClubExterieur = e2;
  }
}

