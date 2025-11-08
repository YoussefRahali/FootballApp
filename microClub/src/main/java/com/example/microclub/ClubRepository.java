package com.example.microclub;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface ClubRepository extends MongoRepository<Club, String> {
    List<Club> findByNameContainingIgnoreCase(String name);
    List<Club> findByPresidentContainingIgnoreCase(String president);
}
