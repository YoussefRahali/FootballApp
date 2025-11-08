package com.example.microlocal;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;



@Document(collection = "locals")
public class Local {

    @Id
    private String id;

    private String name;
    private String city;
    private String address;
    private Integer capacity;
    private TerrainType type;      // Enum stored as string value
    private boolean available;
    private Float pricePerHour;
    private Double latitude;       // GPS coordinates
    private Double longitude;      // GPS coordinates
    private String imageUrl;       // URL to uploaded image
    private Instant createdAt;     // set in Service on create
    private String imageFileId;



    // Default constructor
    public Local() {}

    // Constructor
    public Local(String name, String city, String address, Integer capacity,
                 TerrainType type, boolean available, Float pricePerHour,
                 Double latitude, Double longitude) {
        this.name = name;
        this.city = city;
        this.address = address;
        this.capacity = capacity;
        this.type = type;
        this.available = available;
        this.pricePerHour = pricePerHour;
        this.latitude = latitude;
        this.longitude = longitude;
        this.createdAt = Instant.now(); // Set createdAt at instantiation
    }

    // Getters and Setters

    public String getImageFileId() { return imageFileId; }
    public void setImageFileId(String imageFileId) { this.imageFileId = imageFileId; }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public TerrainType getType() {
        return type;
    }

    public void setType(TerrainType type) {
        this.type = type;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public Float getPricePerHour() {
        return pricePerHour;
    }

    public void setPricePerHour(Float pricePerHour) {
        this.pricePerHour = pricePerHour;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    //  for logging/debugging
    @Override
    public String toString() {
        return "Local{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", city='" + city + '\'' +
                ", address='" + address + '\'' +
                ", capacity=" + capacity +
                ", type=" + type +
                ", available=" + available +
                ", pricePerHour=" + pricePerHour +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", imageUrl='" + imageUrl + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}
