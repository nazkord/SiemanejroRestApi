package com.nazkord.siemajero.User;

import com.nazkord.siemajero.Bet.Bet;

import java.util.List;

public class User {

    private int id;
    private String name;
    private int age;
    private String email;
    private List<Bet> bets;

    public User() {
    }

    public User(int id, String name, int age, String email, List<Bet> bets) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.email = email;
        this.bets = bets;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Bet> getBets() {
        return bets;
    }

    public void setBets(List<Bet> bets) {
        this.bets = bets;
    }
}

