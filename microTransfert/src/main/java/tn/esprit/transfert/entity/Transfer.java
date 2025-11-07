package tn.esprit.transfert.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document(collection = "transfers")
public class Transfer {

    @Id
    private String id;
    private Player player;
    private Team fromTeam;
    private Team toTeam;
    private double finalAmount;
    private String status;
    private LocalDate transferDate;

    public Transfer() {}

    public Transfer(Player player, Team fromTeam, Team toTeam) {
        this.player = player;
        this.fromTeam = fromTeam;
        this.toTeam = toTeam;
        this.finalAmount = player.getEstimatedValue(); // par ex.
        this.status = "COMPLETED";
        this.transferDate = LocalDate.now();
    }

    // getters et setters
    public Player getPlayer() { return player; }
    public void setPlayer(Player player) { this.player = player; }

    public Team getFromTeam() { return fromTeam; }
    public void setFromTeam(Team fromTeam) { this.fromTeam = fromTeam; }

    public Team getToTeam() { return toTeam; }
    public void setToTeam(Team toTeam) { this.toTeam = toTeam; }

    public double getFinalAmount() { return finalAmount; }
    public void setFinalAmount(double finalAmount) { this.finalAmount = finalAmount; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public LocalDate getTransferDate() { return transferDate; }
    public void setTransferDate(LocalDate transferDate) { this.transferDate = transferDate; }
}