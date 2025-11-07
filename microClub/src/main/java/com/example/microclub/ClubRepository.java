package com.example.microclub;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

public interface ClubRepository extends MongoRepository<Club, String> {
}
