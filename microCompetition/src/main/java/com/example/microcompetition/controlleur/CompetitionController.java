package com.example.microcompetition.controlleur;

import com.example.microcompetition.dto.ClubDTO;
import com.example.microcompetition.entity.Competition;
import com.example.microcompetition.entity.TypeCompetition;
import com.example.microcompetition.service.CompetitionService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/competitions")
public class CompetitionController {

    private final CompetitionService service;

    public CompetitionController(CompetitionService service) {
        this.service = service;
    }

    @GetMapping
    public List<Competition> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Competition getById(@PathVariable String id) {
        return service.getById(id);
    }

    @PostMapping
    public Competition add(@RequestBody Competition c) {
        return service.add(c);
    }

    @PutMapping("/{id}")
    public Competition update(@PathVariable String id, @RequestBody Competition c) {
        return service.update(id, c);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        service.delete(id);
    }

    // --- Recherches / filtrages simples ---
    @GetMapping("/search")
    public List<Competition> searchByName(@RequestParam String nom) {
        return service.searchByName(nom);
    }

    @GetMapping("/filter/type")
    public List<Competition> filterByType(@RequestParam TypeCompetition type) {
        return service.filterByType(type);
    }

    @GetMapping("/filter/saison")
    public List<Competition> filterBySaison(@RequestParam String saison) {
        return service.filterBySaison(saison);
    }

    @GetMapping("/filter/period")
    public List<Competition> filterByPeriod(
            @RequestParam LocalDate start,
            @RequestParam LocalDate end) {
        return service.filterByPeriod(start, end);
    }

    @PutMapping("/{competitionId}/clubs/{clubId}")
    public Competition assignClub(@PathVariable String competitionId, @PathVariable String clubId) {
        return service.assignClub(competitionId, clubId);
    }


    @DeleteMapping("/{competitionId}/clubs/{clubId}")
    public Competition removeClub(@PathVariable String competitionId, @PathVariable String clubId) {
        return service.removeClub(competitionId, clubId);
    }

    @GetMapping("/{competitionId}/clubs")
    public List<ClubDTO> getClubs(@PathVariable String competitionId) {
        return service.getClubsForCompetition(competitionId);
    }
}
