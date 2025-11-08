package com.example.microbillet;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ClubDTO {
  private String id;
  private String name;
  private String city;
  private int establishedYear;
  private String president;

}
