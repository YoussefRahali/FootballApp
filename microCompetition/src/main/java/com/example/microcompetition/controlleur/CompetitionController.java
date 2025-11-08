package com.example.microcompetition.controlleur;

import com.example.microcompetition.entity.Competition;
import com.example.microcompetition.entity.TypeCompetition;
import com.example.microcompetition.service.CompetitionService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

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
}
