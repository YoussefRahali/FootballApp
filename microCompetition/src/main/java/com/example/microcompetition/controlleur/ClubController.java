package com.example.microcompetition.controlleur;

import com.example.microcompetition.entity.Club;
import com.example.microcompetition.service.ClubService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/clubs")
@CrossOrigin
public class ClubController {

    private final ClubService service;

    public ClubController(ClubService service) {
        this.service = service;
    }

    @GetMapping
    public List<Club> getTous() {
        return service.getTous();
    }

    @GetMapping("/{id}")
    public Optional<Club> getParId(@PathVariable String id) {
        return service.getParId(id);
    }

    @PostMapping
    public Club ajouter(@RequestBody Club c) {
        return service.ajouter(c);
    }

    @PutMapping("/{id}")
    public Club mettreAJour(@PathVariable String id, @RequestBody Club c) {
        return service.mettreAJour(id, c);
    }

    @DeleteMapping("/{id}")
    public void supprimer(@PathVariable String id) {
        service.supprimer(id);
    }
}
