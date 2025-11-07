package tn.esprit.transfert.DTO;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document(collection = "players")
public class Player {

    @Id
    private String id;
    private String name;
    private int age;
    private String position;
    private int goals;
    private int assists;
    private int appearances;
    private double baseValue;
    private double estimatedValue;
    private LocalDate contractEndDate;

    // --- Getters / Setters ---
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    public String getPosition() { return position; }
    public void setPosition(String position) { this.position = position; }

    public int getGoals() { return goals; }
    public void setGoals(int goals) { this.goals = goals; }

    public int getAssists() { return assists; }
    public void setAssists(int assists) { this.assists = assists; }

    public int getAppearances() { return appearances; }
    public void setAppearances(int appearances) { this.appearances = appearances; }

    public double getBaseValue() { return baseValue; }
    public void setBaseValue(double baseValue) { this.baseValue = baseValue; }

    public double getEstimatedValue() { return estimatedValue; }
    public void setEstimatedValue(double estimatedValue) { this.estimatedValue = estimatedValue; }

    public LocalDate getContractEndDate() { return contractEndDate; }
    public void setContractEndDate(LocalDate contractEndDate) { this.contractEndDate = contractEndDate; }
}
