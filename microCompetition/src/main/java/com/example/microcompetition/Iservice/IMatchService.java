package com.example.microcompetition.Iservice;

import com.example.microcompetition.dto.MatchResultDTO;
import com.example.microcompetition.entity.Match;

import java.util.List;
import java.util.Optional;

public interface IMatchService {

    List<Match> getAll();

    Optional<Match> getById(String id);

    List<Match> getByCompetition(String idCompetition);

    Match add(Match m);

    Match update(String id, Match m);

    Match updateResult(String id, MatchResultDTO dto);

    void delete(String id);
}
