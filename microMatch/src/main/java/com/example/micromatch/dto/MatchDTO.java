package com.example.micromatch.dto;
import java.time.LocalDateTime;

import com.example.micromatch.enums.MatchStatus;
import com.example.micromatch.enums.TunisianReferee;

public class MatchDTO {
    private String team1Id;
    private String team2Id;
    private String localId;
    private LocalDateTime date;
    private MatchStatus status;
    private TunisianReferee referee;
    private Integer scoreTeam1;
    private Integer scoreTeam2;

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

    public String getLocalId() { return localId; }
    public void setLocalId(String localId) { this.localId = localId; }

    public Integer getScoreTeam1() { return scoreTeam1; }
    public void setScoreTeam1(Integer scoreTeam1) { this.scoreTeam1 = scoreTeam1; }

    public Integer getScoreTeam2() { return scoreTeam2; }
    public void setScoreTeam2(Integer scoreTeam2) { this.scoreTeam2 = scoreTeam2; }
}
