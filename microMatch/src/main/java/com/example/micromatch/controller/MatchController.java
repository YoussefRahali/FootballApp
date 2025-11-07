package com.example.micromatch.controller;

import com.example.micromatch.entity.Match;
import com.example.micromatch.service.MatchService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/matches")
public class MatchController {

    private final MatchService matchService;

    public MatchController(MatchService matchService) {
        this.matchService = matchService;
    }

    @GetMapping
    public List<Match> getAllMatches() {
        return matchService.getAll();
    }

    @GetMapping("/{id}")
    public Optional<Match> getMatchById(@PathVariable String id) {
        return matchService.getMatchById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Match createMatch(@RequestBody Match match) {
        return matchService.createMatch(match);
    }

    @PutMapping("/{id}")
    public Match updateMatch(@PathVariable String id, @RequestBody Match matchDetails) {
        return matchService.updateMatch(id, matchDetails);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMatch(@PathVariable String id) {
        matchService.deleteMatch(id);
    }
}