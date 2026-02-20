package tn.esprit.transfert.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@FeignClient(name = "microJoueur")
public interface JoueurClient {

    @GetMapping("/joueurs")
    List<Joueur> all();

    @GetMapping("/joueurs/{id}")
    ResponseEntity<Joueur> one(@PathVariable String id);
}
