package tn.esprit.transfert.client;

public class Club {
    private String id;
    private String name;
    private String city;
    private int establishedYear;
    private String president;
    private double budget;

    // Getter for id
    public String getId() {
        return id;
    }

    // Setter for id
    public void setId(String id) {
        this.id = id;
    }

    // Getter for name
    public String getName() {
        return name;
    }

    // Setter for name
    public void setName(String name) {
        this.name = name;
    }

    // Getter for city
    public String getCity() {
        return city;
    }

    // Setter for city
    public void setCity(String city) {
        this.city = city;
    }

    // Getter for establishedYear
    public int getEstablishedYear() {
        return establishedYear;
    }

    // Setter for establishedYear
    public void setEstablishedYear(int establishedYear) {
        this.establishedYear = establishedYear;
    }

    // Getter for president
    public String getPresident() {
        return president;
    }

    // Setter for president
    public void setPresident(String president) {
        this.president = president;
    }
}
