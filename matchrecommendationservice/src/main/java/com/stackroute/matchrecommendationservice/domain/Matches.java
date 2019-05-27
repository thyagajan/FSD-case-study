package com.stackroute.matchrecommendationservice.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;


public class Matches {

    @JsonProperty("unique_id")
    private int id;

    private Date date;

    @JsonProperty("team-1")
    private String team1;

    @JsonProperty("team-2")
    private String team2;

    private String type;

    @JsonProperty("toss_winner_team")
    private String tossWinnerTeam;

    @JsonProperty("winner_team")
    private String winnerTeam;

    private boolean matchStarted;

    private String description;

    private String title;

    @Override
    public String toString() {
        return "Matches{" +
                "id=" + id +
                ", date=" + date +
                ", team1='" + team1 + '\'' +
                ", team2='" + team2 + '\'' +
                ", type='" + type + '\'' +
                ", tossWinnerTeam='" + tossWinnerTeam + '\'' +
                ", winnerTeam='" + winnerTeam + '\'' +
                ", matchStarted=" + matchStarted +
                ", description='" + description + '\'' +
                ", title='" + title + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTeam1() {
        return team1;
    }

    public void setTeam1(String team1) {
        this.team1 = team1;
    }

    public String getTeam2() {
        return team2;
    }

    public void setTeam2(String team2) {
        this.team2 = team2;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTossWinnerTeam() {
        return tossWinnerTeam;
    }

    public void setTossWinnerTeam(String tossWinnerTeam) {
        this.tossWinnerTeam = tossWinnerTeam;
    }

    public String getWinnerTeam() {
        return winnerTeam;
    }

    public void setWinnerTeam(String winnerTeam) {
        this.winnerTeam = winnerTeam;
    }

    public boolean isMatchStarted() {
        return matchStarted;
    }

    public void setMatchStarted(boolean matchStarted) {
        this.matchStarted = matchStarted;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Matches matches = (Matches) o;
        return id == matches.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
