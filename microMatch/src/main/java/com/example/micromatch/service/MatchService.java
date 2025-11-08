package com.example.micromatch.service;

import com.example.micromatch.communication.Club;
import com.example.micromatch.communication.ClubClient;
import com.example.micromatch.communication.Local;
import com.example.micromatch.communication.LocalClient;
import com.example.micromatch.dto.MatchDTO;
import com.example.micromatch.entity.Match;
import com.example.micromatch.enums.MatchStatus;
import com.example.micromatch.exceptions.MatchNotFoundException;
import com.example.micromatch.repository.MatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class MatchService {

    private final MatchRepository matchRepository;

    public MatchService(MatchRepository matchRepository) {
        this.matchRepository = matchRepository;
    }

    public List<Match> searchMatches(String teamName, LocalDate date, MatchStatus status) {
        LocalDateTime startOfDay = date.atStartOfDay();
        LocalDateTime endOfDay = date.atTime(LocalTime.MAX);
        return matchRepository.findByTeam1IdOrTeam2IdAndDateBetweenAndStatus(teamName, teamName, startOfDay, endOfDay, status);
    }

    public List<String> getScoreHistory(String matchId) {
        return matchRepository.findById(matchId)
                .orElseThrow(() -> new MatchNotFoundException("Match not found with id: " + matchId))
                .getEvents();
    }

    public long calculateTotalMatchDuration(String matchId) {
        Match match = matchRepository.findById(matchId)
                .orElseThrow(() -> new MatchNotFoundException("Match not found with id: " + matchId));
        if (match.getStartTime() != null && match.getEndTime() != null) {
            return Duration.between(match.getStartTime(), match.getEndTime()).toMinutes();
        }
        return 0;
    }

    public Map<String, Integer> getFinalScore(String matchId) {
        Match match = matchRepository.findById(matchId)
                .orElseThrow(() -> new MatchNotFoundException("Match not found with id: " + matchId));
        Map<String, Integer> score = new HashMap<>();
        score.put(match.getTeam1Id(), match.getScoreTeam1());
        score.put(match.getTeam2Id(), match.getScoreTeam2());
        return score;
    }

    public Match createMatch(MatchDTO matchDTO) {
        Match match = new Match();
        match.setTeam1Id(matchDTO.getTeam1Id());
        match.setTeam2Id(matchDTO.getTeam2Id());
        match.setDate(matchDTO.getDate());
        match.setStatus(MatchStatus.PROGRAMME);
        match.setReferee(matchDTO.getReferee());
        return matchRepository.save(match);
    }
    public List<Match> getAllMatches() {
        return matchRepository.findAll();
    }

    public Match getMatchById(String id) {
        return matchRepository.findById(id)
                .orElseThrow(() -> new MatchNotFoundException("Match not found with id: " + id));
    }

    public Match updateMatch(String id, MatchDTO matchDTO) {
        Match match = getMatchById(id);
        match.setTeam1Id(matchDTO.getTeam1Id());
        match.setTeam2Id(matchDTO.getTeam2Id());
        match.setDate(matchDTO.getDate());
        match.setStatus(matchDTO.getStatus());
        match.setReferee(matchDTO.getReferee());
        return matchRepository.save(match);
    }
    public Match startMatch(String matchId) {
        Match match = matchRepository.findById(matchId)
                .orElseThrow(() -> new MatchNotFoundException("Match not found with id: " + matchId));
        match.setStatus(MatchStatus.EN_COURS);
        match.setStartTime(LocalDateTime.now());
        return matchRepository.save(match);
    }

    public Match finishMatch(String matchId) {
        Match match = matchRepository.findById(matchId)
                .orElseThrow(() -> new MatchNotFoundException("Match not found with id: " + matchId));
        match.setStatus(MatchStatus.TERMINE);
        match.setEndTime(LocalDateTime.now());
        if (match.getStartTime() != null) {
            match.setDuration(Duration.between(match.getStartTime(), match.getEndTime()).toMinutes());
        }
        return matchRepository.save(match);
    }

    public Match changePhase(String matchId, String phase) {
        Match match = matchRepository.findById(matchId)
                .orElseThrow(() -> new MatchNotFoundException("Match not found with id: " + matchId));
        match.setPhase(phase);
        return matchRepository.save(match);
    }

    public Match pauseMatch(String matchId) {
        Match match = matchRepository.findById(matchId)
                .orElseThrow(() -> new MatchNotFoundException("Match not found with id: " + matchId));
        match.setStatus(MatchStatus.PAUSE);
        return matchRepository.save(match);
    }

    public Match resumeMatch(String matchId) {
        Match match = matchRepository.findById(matchId)
                .orElseThrow(() -> new MatchNotFoundException("Match not found with id: " + matchId));
        match.setStatus(MatchStatus.EN_COURS);
        return matchRepository.save(match);
    }

    public Match postponeMatch(String matchId, LocalDateTime newDate) {
        Match match = matchRepository.findById(matchId)
                .orElseThrow(() -> new MatchNotFoundException("Match not found with id: " + matchId));
        match.setStatus(MatchStatus.REPORTE);
        match.setDate(newDate);
        return matchRepository.save(match);
    }

    public Match cancelMatch(String matchId) {
        Match match = matchRepository.findById(matchId)
                .orElseThrow(() -> new MatchNotFoundException("Match not found with id: " + matchId));
        match.setStatus(MatchStatus.ANNULE);
        return matchRepository.save(match);
    }

    public Optional<Match> getNextMatch() {
        return matchRepository.findTopByStatusOrderByDateAsc(MatchStatus.PROGRAMME);
    }

    public Optional<Match> getLastMatch() {
        return matchRepository.findTopByStatusOrderByDateDesc(MatchStatus.TERMINE);
    }
    @Autowired
    private ClubClient clubClient;

    public List<Club> getAllClubs(){
        return clubClient.getAllClubs();
    }

    public Optional<Club> getClubById(String id){
        return clubClient.getClubById(id);
    }
    @Autowired
    private LocalClient localClient;
    public List<Local> getAll() {
        return localClient.findAll();
    }

    public Local getById(String id) {
        return localClient.findById(id)
                .orElseThrow(() -> new RuntimeException("Local not found: " + id));
    }

}