package tn.esprit.transfert.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDate;

@Document(collection = "transfers")
public class Transfer {

    @Id
    private String id;
    private String joueurId; // id du joueur
    private String fromClubId; // club d'origine
    private String toClubId;   // club destination
    private double transferFee;
    private LocalDate transferDate;

    public Transfer() {}

    public Transfer(String joueurId, String fromClubId, String toClubId, double transferFee, LocalDate transferDate) {
        this.joueurId = joueurId;
        this.fromClubId = fromClubId;
        this.toClubId = toClubId;
        this.transferFee = transferFee;
        this.transferDate = transferDate;
    }

    // Getters et Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getJoueurId() { return joueurId; }
    public void setJoueurId(String joueurId) { this.joueurId = joueurId; }

    public String getFromClubId() { return fromClubId; }
    public void setFromClubId(String fromClubId) { this.fromClubId = fromClubId; }

    public String getToClubId() { return toClubId; }
    public void setToClubId(String toClubId) { this.toClubId = toClubId; }

    public double getTransferFee() { return transferFee; }
    public void setTransferFee(double transferFee) { this.transferFee = transferFee; }

    public LocalDate getTransferDate() { return transferDate; }
    public void setTransferDate(LocalDate transferDate) { this.transferDate = transferDate; }
}

