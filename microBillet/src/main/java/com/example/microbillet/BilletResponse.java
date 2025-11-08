package com.example.microbillet;

import java.time.Instant;

public class BilletResponse {
  private String id;
  private String matchId;
  private String purchaserId;
  private String seat;
  private double price;
  private String status;
  private Instant createdAt;
  private Instant updatedAt;

  public BilletResponse() {}

  public String getId() { return id; }
  public void setId(String id) { this.id = id; }
  public String getMatchId() { return matchId; }
  public void setMatchId(String matchId) { this.matchId = matchId; }
  public String getPurchaserId() { return purchaserId; }
  public void setPurchaserId(String purchaserId) { this.purchaserId = purchaserId; }
  public String getSeat() { return seat; }
  public void setSeat(String seat) { this.seat = seat; }
  public double getPrice() { return price; }
  public void setPrice(double price) { this.price = price; }
  public String getStatus() { return status; }
  public void setStatus(String status) { this.status = status; }
  public Instant getCreatedAt() { return createdAt; }
  public void setCreatedAt(Instant createdAt) { this.createdAt = createdAt; }
  public Instant getUpdatedAt() { return updatedAt; }
  public void setUpdatedAt(Instant updatedAt) { this.updatedAt = updatedAt; }
}
