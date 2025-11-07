package com.example.microbillet;

import java.time.Instant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BilletResponse {
  private String id;
  private String matchId;
  private String purchaserId;
  private String seat;
  private double price;
  private String status;
  private Instant createdAt;
  private Instant updatedAt;
}