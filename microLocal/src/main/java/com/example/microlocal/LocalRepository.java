package com.example.microlocal;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface LocalRepository extends MongoRepository<Local, String> {
    List<Local> findByCityIgnoreCase(String city);
    List<Local> findByType(TerrainType type);
    List<Local> findByAvailableTrue();
}
