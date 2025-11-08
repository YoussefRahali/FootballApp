package com.example.microclub;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@FeignClient(name = "microJoueur")
public interface JoueurClient {
    @GetMapping("/joueurs")
    List<Joueur> all();

    @GetMapping("/joueurs/{id}")
    Joueur one(@PathVariable String id);
}