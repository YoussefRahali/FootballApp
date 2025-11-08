package com.example.microbillet;

import java.time.Instant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Billet")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Billet {
  @Id
  private String id;
  private String matchId;
  private String purchaserId;
  private String seat; // ex: "A12" ou null si general
  private double price;
  private String status;
  private Instant createdAt;
  private Instant updatedAt;
  private String numero;
  private Double prix;


}
