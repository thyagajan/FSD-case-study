package com.stackroute.rabbitmq.domain;

import java.time.LocalDate;
import java.util.Date;

public class MatchesDTO {


    private int id;

    private Date date;


    private String team1;


    private String team2;

    private String type;


    private String tossWinnerTeam;


    private String winnerTeam;

    private boolean matchStarted;

    private String description;

    private String title;

    private String action;

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

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public MatchesDTO() {
    }
}
