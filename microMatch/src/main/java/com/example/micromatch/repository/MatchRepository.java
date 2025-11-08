package com.example.micromatch.repository;

import com.example.micromatch.entity.Match;
import com.example.micromatch.enums.MatchStatus;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface MatchRepository extends MongoRepository<Match, String> {
    List<Match> findByTeam1IdOrTeam2IdAndDateBetweenAndStatus(String team1Id, String team2Id, LocalDateTime startDate, LocalDateTime endDate, MatchStatus status);
    Optional<Match> findTopByStatusOrderByDateAsc(MatchStatus status);
    Optional<Match> findTopByStatusOrderByDateDesc(MatchStatus status);
}
