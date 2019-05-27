package com.stackroute.favouriteservice.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
public class UserFavourites {

    @Id
    private String userName;
    private List<Matches> matchesList;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<Matches> getMatchesList() {
        return matchesList;
    }

    public void setMatchesList(List<Matches> matchesList) {
        this.matchesList = matchesList;
    }

    public UserFavourites() {
    }

    public UserFavourites(String userName, List<Matches> matchesList) {
        this.userName = userName;
        this.matchesList = matchesList;
    }

    @Override
    public String toString() {
        return "UserFavourites{" +
                "userName='" + userName + '\'' +
                ", matchesList=" + matchesList +
                '}';
    }
}
