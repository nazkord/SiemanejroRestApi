package com.nazkord.siemajero.model.dto.footballData;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "scores")
public class Score {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private String winner;

    @OneToOne
    private FullTimeResult fullTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }

    public FullTimeResult getFullTime() {
        return fullTime;
    }

    public void setFullTime(FullTimeResult fullTime) {
        this.fullTime = fullTime;
    }

    @Override
    public String toString() {
        return "Score{" +
                "id=" + id +
                ", winner='" + winner + '\'' +
                ", fullTime=" + fullTime +
                '}';
    }
}
