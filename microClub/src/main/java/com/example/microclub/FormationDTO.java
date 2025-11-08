package com.example.microclub;

import java.util.List;

public class FormationDTO {
    private String formationName;
    private String description;
    private List<Joueur> goalkeeper;
    private List<Joueur> defenders;
    private List<Joueur> midfielders;
    private List<Joueur> attackers;
    private List<Joueur> substitutes;

    public FormationDTO() {
    }

    public FormationDTO(String formationName, String description, List<Joueur> goalkeeper, 
                       List<Joueur> defenders, List<Joueur> midfielders, 
                       List<Joueur> attackers, List<Joueur> substitutes) {
        this.formationName = formationName;
        this.description = description;
        this.goalkeeper = goalkeeper;
        this.defenders = defenders;
        this.midfielders = midfielders;
        this.attackers = attackers;
        this.substitutes = substitutes;
    }

    // Getters and Setters
    public String getFormationName() {
        return formationName;
    }

    public void setFormationName(String formationName) {
        this.formationName = formationName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Joueur> getGoalkeeper() {
        return goalkeeper;
    }

    public void setGoalkeeper(List<Joueur> goalkeeper) {
        this.goalkeeper = goalkeeper;
    }

    public List<Joueur> getDefenders() {
        return defenders;
    }

    public void setDefenders(List<Joueur> defenders) {
        this.defenders = defenders;
    }

    public List<Joueur> getMidfielders() {
        return midfielders;
    }

    public void setMidfielders(List<Joueur> midfielders) {
        this.midfielders = midfielders;
    }

    public List<Joueur> getAttackers() {
        return attackers;
    }

    public void setAttackers(List<Joueur> attackers) {
        this.attackers = attackers;
    }

    public List<Joueur> getSubstitutes() {
        return substitutes;
    }

    public void setSubstitutes(List<Joueur> substitutes) {
        this.substitutes = substitutes;
    }
}

