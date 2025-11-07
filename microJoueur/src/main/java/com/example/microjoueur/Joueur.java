package com.example.microjoueur;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document(collection = "Joueur")
public class Joueur {
    @Id
    private String id;

    private String nom;
    private String prenom;
    private Integer age;

    // Poste: GK, DEF, MID, ATT...
    private String poste;

    private Integer numero;      // dossard
    private String club;         // club actuel
    private String nationalite;
    private String position;
    private int goals;
    private int assists;
    private int appearances;
    private double baseValue;
    private double estimatedValue;
    private LocalDate contractEndDate;
    // Constructors
    public Joueur() {
    }

    public Joueur(String nom, String prenom, Integer age, String poste, Integer numero, String club, String nationalite) {
        this.nom = nom;
        this.prenom = prenom;
        this.age = age;
        this.poste = poste;
        this.numero = numero;
        this.club = club;
        this.nationalite = nationalite;
    }

    public Joueur(String id, String nom, String prenom, Integer age, String poste, Integer numero, String club, String nationalite) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.age = age;
        this.poste = poste;
        this.numero = numero;
        this.club = club;
        this.nationalite = nationalite;
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

    public String getPoste() {
        return poste;
    }

    public void setPoste(String poste) {
        this.poste = poste;
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

    @Override
    public String toString() {
        return "Joueur{" +
                "id='" + id + '\'' +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", age=" + age +
                ", poste='" + poste + '\'' +
                ", numero=" + numero +
                ", club='" + club + '\'' +
                ", nationalite='" + nationalite + '\'' +
                '}';
    }
}
