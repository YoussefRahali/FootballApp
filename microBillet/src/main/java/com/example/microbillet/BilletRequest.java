package com.example.microbillet;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class BilletRequest {
  @NotBlank private String matchId;
  @NotBlank private String purchaserId;
  private String seat;
  @NotNull private Double price;

  public BilletRequest() {}
  public String getMatchId() { return matchId; }
  public void setMatchId(String matchId) { this.matchId = matchId; }
  public String getPurchaserId() { return purchaserId; }
  public void setPurchaserId(String purchaserId) { this.purchaserId = purchaserId; }
  public String getSeat() { return seat; }
  public void setSeat(String seat) { this.seat = seat; }
  public Double getPrice() { return price; }
  public void setPrice(Double price) { this.price = price; }
}
