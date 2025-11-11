package com.example.micromatch.service;

import com.example.micromatch.communication.Club;
import com.example.micromatch.communication.ClubClient;
import com.example.micromatch.entity.Match;
import com.example.micromatch.enums.MatchStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class ChatbotService {
    private final MatchService matchService;
    
    @Autowired
    private ClubClient clubClient;
    
    // Cache pour les noms des clubs
    private Map<String, String> clubNamesCache = new HashMap<>();

    public ChatbotService(MatchService matchService) {
        this.matchService = matchService;
    }
    
    /**
     * Récupère le nom d'un club par son ID
     */
    private String getClubName(String clubId) {
        if (clubId == null || clubId.isEmpty()) {
            return "Unknown Team";
        }
        
        // Vérifier le cache
        if (clubNamesCache.containsKey(clubId)) {
            return clubNamesCache.get(clubId);
        }
        
        try {
            Optional<Club> club = clubClient.getClubById(clubId);
            if (club.isPresent() && club.get().getName() != null) {
                String clubName = club.get().getName();
                clubNamesCache.put(clubId, clubName);
                return clubName;
            }
        } catch (Exception e) {
            System.err.println("Error fetching club name for ID " + clubId + ": " + e.getMessage());
        }
        
        // Si on ne trouve pas le nom, retourner l'ID
        return clubId;
    }
    
    /**
     * Récupère les noms des deux équipes d'un match
     */
    private String formatMatchTeams(Match match) {
        String team1Name = getClubName(match.getTeam1Id());
        String team2Name = getClubName(match.getTeam2Id());
        return team1Name + " vs " + team2Name;
    }

    public String getResponse(String query) {
        String lowerCaseQuery = query.toLowerCase();

        if (lowerCaseQuery.contains("score history")) {
            String matchId = extractMatchId(lowerCaseQuery);
            return getScoreHistory(matchId);
        } else if (lowerCaseQuery.startsWith("search matches of team")) {
            return searchMatches(lowerCaseQuery);
        } else if (lowerCaseQuery.contains("how long did match")) {
            String matchId = extractMatchId(lowerCaseQuery);
            return getTotalMatchDuration(matchId);
        } else if (lowerCaseQuery.contains("next match")) {
            return getNextMatch();
        } else if (lowerCaseQuery.contains("last match")) {
            return getLastMatch();
        } else {
            return "I'm sorry, I can only provide information about match schedules, scores, and statistics.";
        }
    }

    private String getScoreHistory(String matchId) {
        if (matchId == null) {
            return "Please provide a match ID.";
        }
        List<String> scoreHistory = matchService.getScoreHistory(matchId);
        return "Score history for match " + matchId + ":\n" + String.join("\n", scoreHistory);
    }

    private String searchMatches(String query) {
        String teamName = extractTeamName(query);
        if (teamName == null) {
            return "Please provide a team name.";
        }
        List<Match> matches = matchService.searchMatches(teamName, LocalDate.now().plusWeeks(1), MatchStatus.PROGRAMME);
        if (matches.isEmpty()) {
            return "No matches found for team " + teamName + " next week.";
        }
        return "Matches for team " + teamName + " next week:\n" +
                matches.stream()
                        .map(match -> formatMatchTeams(match) + " on " + match.getDate())
                        .collect(Collectors.joining("\n"));
    }

    private String getTotalMatchDuration(String matchId) {
        if (matchId == null) {
            return "Please provide a match ID.";
        }
        long duration = matchService.calculateTotalMatchDuration(matchId);
        return "Match " + matchId + " lasted " + duration + " minutes.";
    }

    private String getNextMatch() {
        Optional<Match> nextMatch = matchService.getNextMatch();
        return nextMatch.map(match -> "The next match is " + formatMatchTeams(match) + " on " + match.getDate())
                .orElse("No upcoming matches found.");
    }

    private String getLastMatch() {
        Optional<Match> lastMatch = matchService.getLastMatch();
        return lastMatch.map(match -> "The last match was " + formatMatchTeams(match) + " with a final score of " + match.getScoreTeam1() + "-" + match.getScoreTeam2() + ".")
                .orElse("No finished matches found.");
    }

    private String extractMatchId(String query) {
        Pattern pattern = Pattern.compile("match (\\w+)");
        Matcher matcher = pattern.matcher(query);
        if (matcher.find()) {
            return matcher.group(1);
        }
        return null;
    }

    private String extractTeamName(String query) {
        Pattern pattern = Pattern.compile("team (\\w+)");
        Matcher matcher = pattern.matcher(query);
        if (matcher.find()) {
            return matcher.group(1);
        }
        return null;
    }
}
