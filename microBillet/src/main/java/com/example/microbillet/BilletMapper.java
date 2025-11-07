package com.example.microbillet;


import java.time.Instant;

public final class BilletMapper {

  private BilletMapper() {}

  public static Billet toEntity(BilletRequest req) {
    return Billet.builder()
        .matchId(req.getMatchId())
        .purchaserId(req.getPurchaserId())
        .seat(req.getSeat())
        .price(req.getPrice() != null ? req.getPrice() : 0.0)
        .status("RESERVED")
        .createdAt(Instant.now())
        .updatedAt(Instant.now())
        .build();
  }

  public static BilletResponse toResponse(Billet b) {
    if (b == null) return null;
    return BilletResponse.builder()
        .id(b.getId())
        .matchId(b.getMatchId())
        .purchaserId(b.getPurchaserId())
        .seat(b.getSeat())
        .price(b.getPrice())
        .status(b.getStatus())
        .createdAt(b.getCreatedAt())
        .updatedAt(b.getUpdatedAt())
        .build();
  }
}
