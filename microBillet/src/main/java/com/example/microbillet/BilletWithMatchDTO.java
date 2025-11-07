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

  private MatchDTO match;
}

