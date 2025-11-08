package com.example.microcompetition.repository;

import com.example.microcompetition.entity.Competition;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CompetitionRepository extends MongoRepository<Competition, String> {}

