package com.example.microcompetition.dto;

public class ClubDTO {
    private String id;
    private String name;
    private String city;
    private int establishedYear;
    private String president;
    private double budget;

    // Getters & Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }

    public int getEstablishedYear() { return establishedYear; }
    public void setEstablishedYear(int establishedYear) { this.establishedYear = establishedYear; }

    public String getPresident() { return president; }
    public void setPresident(String president) { this.president = president; }

    public double getBudget() { return budget; }
    public void setBudget(double budget) { this.budget = budget; }
}
