package tn.esprit.transfert.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDate;

@Document(collection = "offers")
public class Offer {

    @Id
    private String id;
    private String joueurId;
    private String clubId; // club qui fait l’offre
    private double amount;
    private LocalDate offerDate;
    private String status; // PENDING / ACCEPTED / REJECTED

    public Offer() {}

    public Offer(String joueurId, String clubId, double amount, LocalDate offerDate, String status) {
        this.joueurId = joueurId;
        this.clubId = clubId;
        this.amount = amount;
        this.offerDate = offerDate;
        this.status = status;
    }

    // Getters & Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getJoueurId() { return joueurId; }
    public void setJoueurId(String joueurId) { this.joueurId = joueurId; }

    public String getClubId() { return clubId; }
    public void setClubId(String clubId) { this.clubId = clubId; }

    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }

    public LocalDate getOfferDate() { return offerDate; }
    public void setOfferDate(LocalDate offerDate) { this.offerDate = offerDate; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}

