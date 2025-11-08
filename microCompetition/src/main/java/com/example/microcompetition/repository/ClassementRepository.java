package com.example.microcompetition.repository;

import com.example.microcompetition.entity.Classement;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ClassementRepository extends MongoRepository<Classement, String> {
  //  List<Classement> findByIdCompetition(String idCompetition);

}
