package com.nazkord.siemajero.model.dto.footballData;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "matches")
@Entity
public class Match {

    @Id
    private Long id;

    @OneToOne
    private Competition competition;
    private String utcDate;
    private String status;

    @OneToOne
    private Score score;
    private Integer matchday;

    @ManyToOne
    @JoinColumn(name = "home_team_id")
    private FootBallTeam homeTeam;

    @ManyToOne
    @JoinColumn(name = "away_team_id")
    private FootBallTeam awayTeam;

    public Integer getMatchday() {
        return matchday;
    }

    public void setMatchday(Integer matchday) {
        this.matchday = matchday;
    }

    public Score getScore() {
        return score;
    }

    public void setScore(Score score) {
        this.score = score;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUtcDate() {
        return utcDate;
    }

    public void setUtcDate(String utcDate) {
        this.utcDate = utcDate;
    }

    public Competition getCompetition() {
        return competition;
    }

    public void setCompetition(Competition competition) {
        this.competition = competition;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public FootBallTeam getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(FootBallTeam homeTeam) {
        this.homeTeam = homeTeam;
    }

    public FootBallTeam getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(FootBallTeam awayTeam) {
        this.awayTeam = awayTeam;
    }

    @Override
    public String toString() {
        return "Match{" +
                "id=" + id +
                ", competition=" + competition +
                ", utcDate='" + utcDate + '\'' +
                ", status=" + status +
                ", score=" + score +
                ", matchday=" + matchday +
                ", homeTeam=" + homeTeam +
                ", awayTeam=" + awayTeam +
                '}';
    }
}
