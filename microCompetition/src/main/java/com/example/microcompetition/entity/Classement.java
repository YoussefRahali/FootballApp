package com.example.microcompetition.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "standings")
public class Classement {

    @Id
    private String id;

    // ID du club et du match venant de microservices
    private String clubId;

    private int matchsJoues;
    private int victoires;
    private int nuls;
    private int defaites;
    private int points;

    // --- Constructeurs ---
    public Classement() {}

    public Classement(String id, String clubId, String matchId, int matchsJoues,
                      int victoires, int nuls, int defaites) {
        this.id = id;
        this.clubId = clubId;
        this.matchsJoues = matchsJoues;
        this.victoires = victoires;
        this.nuls = nuls;
        this.defaites = defaites;
        calculerPoints();
    }

    // --- Getters & Setters ---
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getClubId() { return clubId; }
    public void setClubId(String clubId) { this.clubId = clubId; }



    public int getMatchsJoues() { return matchsJoues; }
    public void setMatchsJoues(int matchsJoues) { this.matchsJoues = matchsJoues; }

    public int getVictoires() { return victoires; }
    public void setVictoires(int victoires) { this.victoires = victoires; calculerPoints(); }

    public int getNuls() { return nuls; }
    public void setNuls(int nuls) { this.nuls = nuls; calculerPoints(); }

    public int getDefaites() { return defaites; }
    public void setDefaites(int defaites) { this.defaites = defaites; }

    public int getPoints() { return points; }
    public void setPoints(int points) { this.points = points; }

    // --- Calcul automatique des points ---
    public void calculerPoints() {
        this.points = this.victoires * 3 + this.nuls;
    }
}
