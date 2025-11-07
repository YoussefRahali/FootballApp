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
  private Double prix;
  private String equipe1;
  private String equipe2;
  private String stade;
  private String dateMatch;

  public BilletWithMatchDTO(Billet billet, MatchDTO match) {
    this.id = billet.getId();
    this.numero = billet.getNumero();
    this.prix = billet.getPrix();
    this.equipe1 = match.getEquipe1();
    this.equipe2 = match.getEquipe2();
    this.dateMatch = String.valueOf(match.getDateMatch());
  }
}

