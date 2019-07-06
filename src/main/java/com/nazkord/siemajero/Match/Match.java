package com.nazkord.siemajero.Match;

import com.nazkord.siemajero.Model.Score;

public class Match {
    private String homeTeam;
    private String awayTeam;
    private Score score;
    private int match_id;

    public Match() {
    }

    public Match(String homeTeam, String awayTeam, Score score, int match_id) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.score = score;
        this.match_id = match_id;
    }

    public String getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(String homeTeam) {
        this.homeTeam = homeTeam;
    }

    public String getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(String awayTeam) {
        this.awayTeam = awayTeam;
    }

    public Score getScore() {
        return score;
    }

    public void setScore(Score score) {
        this.score = score;
    }

    public int getMatch_id() {
        return match_id;
    }

    public void setMatch_id(int match_id) {
        this.match_id = match_id;
    }
}
