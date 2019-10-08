package com.nazkord.siemajero.model;

import com.nazkord.siemajero.model.dto.footballData.Match;
import com.nazkord.siemajero.model.dto.footballData.Score;

import javax.persistence.*;

@Entity
public class Bet {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @OneToOne
    private Match match;

    @OneToOne
    private User user;

    @OneToOne
    private Score score;

    private Integer result;

    public Bet(Long id, Match match, User user, Score score, int result) {
        this.id = id;
        this.match = match;
        this.user = user;
        this.score = score;
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

    public Score getScore() {
        return score;
    }

    public void setScore(Score score) {
        this.score = score;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public void copyFrom(Bet bet) {
        setMatch(bet.getMatch());
        setUser(bet.getUser());
        setScore(bet.getScore());
        setResult(bet.getResult());
    }
}
