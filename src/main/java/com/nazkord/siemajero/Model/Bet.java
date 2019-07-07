package com.nazkord.siemajero.Model;

public class Bet {
    private Long id;
    private Match match;
    private User user;
    private Score matchScore;
    // TODO: make result ENUM class
    private int result;

    public Bet(Long id, Match match, User user, Score matchScore, int result) {
        this.id = id;
        this.match = match;
        this.user = user;
        this.matchScore = matchScore;
        this.result = result;
    }

    public Bet() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Match getMatch() {
        return match;
    }

    public void setMatch(Match match) {
        this.match = match;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
