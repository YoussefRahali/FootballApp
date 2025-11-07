package tn.esprit.transfert.DTO;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

@Document(collection = "teams")
public class Team {

    @Id
    private String id;

    private String name;
    private double budget;

    @DBRef(lazy = false)
    private Set<Player> squad;

    private String strategy;

    // --- Constructeurs ---
    public Team() {
    }

    public Team(String id, String name, double budget, Set<Player> squad, String strategy) {
        this.id = id;
        this.name = name;
        this.budget = budget;
        this.squad = squad;
        this.strategy = strategy;
    }

    // --- Getters / Setters ---
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public double getBudget() { return budget; }
    public void setBudget(double budget) { this.budget = budget; }

    public Set<Player> getSquad() { return squad; }
    public void setSquad(Set<Player> squad) { this.squad = squad; }

    public String getStrategy() { return strategy; }
    public void setStrategy(String strategy) { this.strategy = strategy; }
}
