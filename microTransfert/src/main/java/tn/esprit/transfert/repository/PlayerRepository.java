package tn.esprit.transfert.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.transfert.entity.Player;

import java.util.Optional;

@Repository
public interface PlayerRepository extends MongoRepository<Player, String> {
    Optional<Player> findByNameIgnoreCase(String name);
}