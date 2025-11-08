package com.example.micromatch.communication;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;


public class Local {

    private String id;

    private String name;
    private String city;
    private String address;
    private Integer capacity;
    private boolean available;

    public Local() {}

    // Constructor
    public Local(String name, String city, String address, Integer capacity,
                  boolean available) {
        this.name = name;
        this.city = city;
        this.address = address;
        this.capacity = capacity;
        this.available = available;
    }

    // Getters and Setters
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


    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
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
                ", available=" + available +
                '}';
    }
}
