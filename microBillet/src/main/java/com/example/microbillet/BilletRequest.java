package com.example.microbillet;



import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BilletRequest {
  @NotBlank
  private String matchId;

  @NotBlank
  private String purchaserId;

  private String seat;

  @NotNull
  @Min(0)
  private Double price;
}

