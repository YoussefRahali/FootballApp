package tn.esprit.transfert.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.transfert.DTO.Player;

import java.util.List;
import java.util.Optional;


@FeignClient(name = "player-s", url = "http://localhost:8400")
public interface PlayerClient {


    @GetMapping
    public List<Player> all();

    @GetMapping("/{id}")
    public ResponseEntity<Player> one(@PathVariable String id);
    @PostMapping
    public Player create(@RequestBody Player j);
    @PutMapping("/{id}")
    public ResponseEntity<Player> update(@PathVariable String id, @RequestBody Player j);

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id);
    Optional<Player> findByNameIgnoreCase(String name);

}