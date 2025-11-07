package com.example.microbillet;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BilletRepository extends MongoRepository<Billet, String> {
  List<Billet> findByMatchId(String matchId);
  List<Billet> findByPurchaserId(String purchaserId);
  List<Billet> findByStatus(String status);

}
