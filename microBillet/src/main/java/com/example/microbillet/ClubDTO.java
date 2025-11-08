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

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public int getEstablishedYear() {
    return establishedYear;
  }

  public void setEstablishedYear(int establishedYear) {
    this.establishedYear = establishedYear;
  }

  public String getPresident() {
    return president;
  }

  public void setPresident(String president) {
    this.president = president;
  }
}
