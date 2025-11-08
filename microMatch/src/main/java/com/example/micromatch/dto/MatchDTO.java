package com.example.micromatch.dto;
import java.time.LocalDateTime;

import com.example.micromatch.enums.MatchStatus;
import com.example.micromatch.enums.TunisianReferee;

public class MatchDTO {
    private String team1Id;
    private String team2Id;
    private LocalDateTime date;
    private MatchStatus status;
    private TunisianReferee referee;

    public MatchDTO() {}

    public String getTeam1Id() { return team1Id; }
    public void setTeam1Id(String team1Id) { this.team1Id = team1Id; }

    public String getTeam2Id() { return team2Id; }
    public void setTeam2Id(String team2Id) { this.team2Id = team2Id; }

    public LocalDateTime getDate() { return date; }
    public void setDate(LocalDateTime date) { this.date = date; }

    public MatchStatus getStatus() { return status; }
    public void setStatus(MatchStatus status) { this.status = status; }

    public TunisianReferee getReferee() { return referee; }
    public void setReferee(TunisianReferee referee) { this.referee = referee; }
}
