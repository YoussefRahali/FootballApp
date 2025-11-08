package com.example.micromatch.entity;

import com.example.micromatch.enums.MatchStatus;
import com.example.micromatch.enums.TunisianReferee;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Document(collection = "matches")
public class Match {

    @Id
    private String id;
    private String team1Id;
    private String team2Id;
    private LocalDateTime date;
    private MatchStatus status;
    private int scoreTeam1;
    private int scoreTeam2;
    private String phase;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private long duration;
    private List<String> events = new ArrayList<>();
    private TunisianReferee referee;

    public Match() {
    }

    public Match(String id, String idCompetition, String idClubDomicile, String idClubExterieur,
        int butsDomicile, int butsExterieur, LocalDate dateMatch,
        StatutMatch statut, VainqueurMatch vainqueur,
        String stade, boolean domicile, TourMatch tour) {
        this.id = id;
        this.idCompetition = idCompetition;
        this.idClubDomicile = idClubDomicile;
        this.idClubExterieur = idClubExterieur;
        this.butsDomicile = butsDomicile;
        this.butsExterieur = butsExterieur;
        this.dateMatch = dateMatch;
        this.statut = statut;
        this.vainqueur = vainqueur;
        this.stade = stade;
        this.domicile = domicile;
        this.tour = tour;
    }

    // --- Getters et Setters ---
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTeam1Id() {
        return team1Id;
    }

    public void setTeam1Id(String team1Id) {
        this.team1Id = team1Id;
    }

    public String getTeam2Id() {
        return team2Id;
    }

    public void setTeam2Id(String team2Id) {
        this.team2Id = team2Id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public MatchStatus getStatus() {
        return status;
    }

    public void setStatus(MatchStatus status) {
        this.status = status;
    }

    public int getScoreTeam1() {
        return scoreTeam1;
    }

    public void setScoreTeam1(int scoreTeam1) {
        this.scoreTeam1 = scoreTeam1;
    }

    public int getScoreTeam2() {
        return scoreTeam2;
    }

    public void setScoreTeam2(int scoreTeam2) {
        this.scoreTeam2 = scoreTeam2;
    }

    public String getPhase() {
        return phase;
    }

    public void setPhase(String phase) {
        this.phase = phase;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public List<String> getEvents() {
        return events;
    }

    public void setEvents(List<String> events) {
        this.events = events;
    }

    public TunisianReferee getReferee() {
        return referee;
    }

    public void setReferee(TunisianReferee referee) {
        this.referee = referee;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Match match = (Match) o;
        return scoreTeam1 == match.scoreTeam1 && scoreTeam2 == match.scoreTeam2 && duration == match.duration && Objects.equals(id, match.id) && Objects.equals(team1Id, match.team1Id) && Objects.equals(team2Id, match.team2Id) && Objects.equals(date, match.date) && status == match.status && Objects.equals(phase, match.phase) && Objects.equals(startTime, match.startTime) && Objects.equals(endTime, match.endTime) && Objects.equals(events, match.events) && referee == match.referee;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, team1Id, team2Id, date, status, scoreTeam1, scoreTeam2, phase, startTime, endTime, duration, events, referee);
    }

    @Override
    public String toString() {
        return "Match{" +
                "id='" + id + '\'' +
                ", team1Id='" + team1Id + '\'' +
                ", team2Id='" + team2Id + '\'' +
                ", date=" + date +
                ", status=" + status +
                ", scoreTeam1=" + scoreTeam1 +
                ", scoreTeam2=" + scoreTeam2 +
                ", phase='" + phase + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", duration=" + duration +
                ", events=" + events +
                ", referee=" + referee +
                '}';
    }
}