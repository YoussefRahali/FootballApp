package com.example.microbillet;

import java.time.Instant;

public final class BilletMapper {
  private BilletMapper() {}

  public static Billet toEntity(BilletRequest req) {
    Billet b = new Billet();
    b.setMatchId(req.getMatchId());
    b.setPurchaserId(req.getPurchaserId());
    b.setSeat(req.getSeat());
    b.setPrice(req.getPrice() != null ? req.getPrice() : 0.0);
    b.setStatus("RESERVED");
    b.setCreatedAt(Instant.now());
    b.setUpdatedAt(Instant.now());
    return b;
  }

  public static BilletResponse toResponse(Billet b) {
    if (b == null) return null;
    BilletResponse r = new BilletResponse();
    r.setId(b.getId());
    r.setMatchId(b.getMatchId());
    r.setPurchaserId(b.getPurchaserId());
    r.setSeat(b.getSeat());
    r.setPrice(b.getPrice());
    r.setStatus(b.getStatus());
    r.setCreatedAt(b.getCreatedAt());
    r.setUpdatedAt(b.getUpdatedAt());
    return r;
  }
}
