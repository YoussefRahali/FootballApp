package com.example.microbillet;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MatchDTO {
  private String id;
  private String equipe1;
  private String equipe2;
  private LocalDate dateMatch;
}
