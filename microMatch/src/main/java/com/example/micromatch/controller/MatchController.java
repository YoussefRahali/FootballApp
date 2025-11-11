package com.example.micromatch.controller;

import com.example.micromatch.communication.Club;
import com.example.micromatch.communication.Local;
import com.example.micromatch.dto.MatchDTO;
import com.example.micromatch.entity.Match;
import com.example.micromatch.enums.MatchStatus;
import com.example.micromatch.service.MatchService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
//@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/matches")
public class MatchController {

    private final MatchService matchService;

    public MatchController(MatchService matchService) {
        this.matchService = matchService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Match createMatch(@RequestBody MatchDTO matchDTO) {
        if (matchDTO.getTeam1Id() == null || matchDTO.getTeam1Id().trim().isEmpty()) {
            throw new IllegalArgumentException("Team1 ID is required");
        }
        if (matchDTO.getTeam2Id() == null || matchDTO.getTeam2Id().trim().isEmpty()) {
            throw new IllegalArgumentException("Team2 ID is required");
        }
        if (matchDTO.getDate() == null) {
            throw new IllegalArgumentException("Date is required");
        }
        if (matchDTO.getReferee() == null) {
            throw new IllegalArgumentException("Referee is required");
        }
        return matchService.createMatch(matchDTO);
    }
    @GetMapping
    public List<Match> getAllMatches() {
        return matchService.getAllMatches();
    }

    @GetMapping("/{id}")
    public Match getMatchById(@PathVariable String id) {
        return matchService.getMatchById(id);
    }

    @PutMapping("/{id}")
    public Match updateMatch(@PathVariable String id, @RequestBody MatchDTO matchDTO) {
        return matchService.updateMatch(id, matchDTO);
    }
    @GetMapping("/search")
    public List<Match> searchMatches(@RequestParam String teamName,
                                     @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
                                     @RequestParam MatchStatus status) {
        return matchService.searchMatches(teamName, date, status);
    }

    @GetMapping("/{id}/score-history")
    public List<String> getScoreHistory(@PathVariable String id) {
        return matchService.getScoreHistory(id);
    }

    @GetMapping("/{id}/duration")
    public long calculateTotalMatchDuration(@PathVariable String id) {
        return matchService.calculateTotalMatchDuration(id);
    }

    @GetMapping("/{id}/final-score")
    public Map<String, Integer> getFinalScore(@PathVariable String id) {
        return matchService.getFinalScore(id);
    }

    @PutMapping("/{id}/start")
    public Match startMatch(@PathVariable String id) {
        return matchService.startMatch(id);
    }

    @PutMapping("/{id}/finish")
    public Match finishMatch(@PathVariable String id) {
        return matchService.finishMatch(id);
    }

    @PutMapping("/{id}/phase")
    public Match changePhase(@PathVariable String id, @RequestParam String phase) {
        return matchService.changePhase(id, phase);
    }

    @PutMapping("/{id}/pause")
    public Match pauseMatch(@PathVariable String id) {
        return matchService.pauseMatch(id);
    }

    @PutMapping("/{id}/resume")
    public Match resumeMatch(@PathVariable String id) {
        return matchService.resumeMatch(id);
    }

    @PutMapping("/{id}/postpone")
    public Match postponeMatch(@PathVariable String id, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime newDate) {
        return matchService.postponeMatch(id, newDate);
    }

    @PutMapping("/{id}/cancel")
    public Match cancelMatch(@PathVariable String id) {
        return matchService.cancelMatch(id);
    }

    @GetMapping("/clubs")
    public List<Club> getAllClubs() {
        return matchService.getAllClubs();
    }

    @GetMapping("/clubs/{id}")
    public Optional<Club> getClubById(@PathVariable String id) {
        return matchService.getClubById(id);
    }


    @GetMapping("/locals")
    public List<Local> getAllLocals() {
        return matchService.getAll();
    }

    @GetMapping("/locals/{id}")
    public Local getLocalById(@PathVariable String id) {
        return matchService.getById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMatch(@PathVariable String id) {
        matchService.deleteMatch(id);
    }

}
