package tn.esprit.transfert.client;

import java.time.LocalDate;

public class Joueur {
    private String id;

    private String nom;
    private String prenom;
    private Integer age;

    // Position: GK, DEF, MID, ATT...
    private String position;

    private Integer numero;      // dossard
    private String club;         // club actuel
    private String nationalite;
    private int goals;
    private int assists;
    private int appearances;
    private double baseValue;
    private double estimatedValue;
    private LocalDate contractEndDate;
    // Constructors
    public Joueur() {
    }

    public Joueur(String nom, String prenom, Integer age, String position, Integer numero, String club, String nationalite, int goals, int assists, int appearances, double baseValue, double estimatedValue, LocalDate contractEndDate) {
        this.nom = nom;
        this.prenom = prenom;
        this.age = age;
        this.position = position;
        this.numero = numero;
        this.club = club;
        this.nationalite = nationalite;
        this.goals = goals;
        this.assists = assists;
        this.appearances = appearances;
        this.baseValue = baseValue;
        this.estimatedValue = estimatedValue;
        this.contractEndDate = contractEndDate;
    }

    public Joueur(String id, String nom, String prenom, Integer age, String position, Integer numero, String club, String nationalite, int goals, int assists, int appearances, double baseValue, double estimatedValue, LocalDate contractEndDate) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.age = age;
        this.position = position;
        this.numero = numero;
        this.club = club;
        this.nationalite = nationalite;
        this.goals = goals;
        this.assists = assists;
        this.appearances = appearances;
        this.baseValue = baseValue;
        this.estimatedValue = estimatedValue;
        this.contractEndDate = contractEndDate;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getClub() {
        return club;
    }

    public void setClub(String club) {
        this.club = club;
    }

    public String getNationalite() {
        return nationalite;
    }

    public void setNationalite(String nationalite) {
        this.nationalite = nationalite;
    }

    public int getGoals() {
        return goals;
    }

    public void setGoals(int goals) {
        this.goals = goals;
    }

    public int getAssists() {
        return assists;
    }

    public void setAssists(int assists) {
        this.assists = assists;
    }

    public int getAppearances() {
        return appearances;
    }

    public void setAppearances(int appearances) {
        this.appearances = appearances;
    }

    public double getBaseValue() {
        return baseValue;
    }

    public void setBaseValue(double baseValue) {
        this.baseValue = baseValue;
    }

    public double getEstimatedValue() {
        return estimatedValue;
    }

    public void setEstimatedValue(double estimatedValue) {
        this.estimatedValue = estimatedValue;
    }

    public LocalDate getContractEndDate() {
        return contractEndDate;
    }

    public void setContractEndDate(LocalDate contractEndDate) {
        this.contractEndDate = contractEndDate;
    }

    @Override
    public String toString() {
        return "Joueur{" +
                "id='" + id + '\'' +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", age=" + age +
                ", position='" + position + '\'' +
                ", numero=" + numero +
                ", club='" + club + '\'' +
                ", nationalite='" + nationalite + '\'' +
                ", goals=" + goals +
                ", assists=" + assists +
                ", appearances=" + appearances +
                ", baseValue=" + baseValue +
                ", estimatedValue=" + estimatedValue +
                ", contractEndDate=" + contractEndDate +
                '}';
    }
}
