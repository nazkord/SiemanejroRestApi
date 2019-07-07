package com.nazkord.siemajero.Model;

import com.nazkord.siemajero.Model.Bet;

import java.util.List;

public class User {

    private Long id;
    private String name;
    private List<Bet> bets;

    public User() {
    }

    public User(Long id, String name, List<Bet> bets) {
        this.id = id;
        this.name = name;
        this.bets = bets;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public List<Bet> getBets() {
        return bets;
    }

    public void setBets(List<Bet> bets) {
        this.bets = bets;
    }
}

