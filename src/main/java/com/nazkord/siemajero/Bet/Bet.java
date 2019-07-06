package com.nazkord.siemajero.Bet;

import com.nazkord.siemajero.Model.Score;

public class Bet {
    private int bet_id;
    private int match_id;
    private int user_id;
    private Score matchScore;
    private int result;

    public Bet() {
    }

    public Bet(int bet_id, int match_id, int user_id, Score matchScore, int result) {
        this.bet_id = bet_id;
        this.match_id = match_id;
        this.user_id = user_id;
        this.matchScore = matchScore;
        this.result = result;
    }

    public int getBet_id() {
        return bet_id;
    }

    public void setBet_id(int bet_id) {
        this.bet_id = bet_id;
    }

    public int getMatch_id() {
        return match_id;
    }

    public void setMatch_id(int match_id) {
        this.match_id = match_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public Score getMatchScore() {
        return matchScore;
    }

    public void setMatchScore(Score matchScore) {
        this.matchScore = matchScore;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }
}
