package com.example.micromatch.service;


import com.example.micromatch.entity.Match;
import com.example.micromatch.repository.MatchRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MatchService   {

    private final MatchRepository matchRepository;

    public MatchService(MatchRepository matchRepository) {
        this.matchRepository = matchRepository;
    }

    public List<Match> getAll() {
        return matchRepository.findAll();
    }

    public Optional<Match> getMatchById(String id) {
        return matchRepository.findById(id);
    }


    public Match createMatch(Match m) {
        return matchRepository.save(m);
    }

    public Match updateMatch(String id, Match m) {
        m.setId(id);
        return matchRepository.save(m);
    }
    public void deleteMatch(String id) {
        matchRepository.deleteById(id);
    }

}
