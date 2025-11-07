package tn.esprit.transfert.repository;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.transfert.entity.Transfer;


@Repository
public interface TransferRepository extends MongoRepository<Transfer, String> {
}
