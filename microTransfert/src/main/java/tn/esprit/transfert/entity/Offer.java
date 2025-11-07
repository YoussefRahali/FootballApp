package tn.esprit.transfert.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import tn.esprit.transfert.DTO.Player;
import tn.esprit.transfert.DTO.Team;

import java.time.LocalDateTime;

@Document(collection = "offers")
public class Offer {

    @Id
    private String id;

    private double amount;

    private Status status;

    private LocalDateTime createdAt;

    @DBRef
    private Team buyer;

    @DBRef
    private Player player;

    // Constructeur vide
    public Offer() {
    }

    // Getters et Setters classiques

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Team getBuyer() {
        return buyer;
    }

    public void setBuyer(Team buyer) {
        this.buyer = buyer;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
