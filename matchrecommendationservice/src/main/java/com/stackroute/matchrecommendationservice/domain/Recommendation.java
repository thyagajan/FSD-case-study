package com.stackroute.matchrecommendationservice.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.annotation.Generated;

@Document
public class Recommendation {
    @Id
    private int id;
    private int count;
    private Matches matches;

    public Recommendation() {
    }

    public Recommendation(int id, int count, Matches matches) {
        this.id = id;
        this.count = count;
        this.matches = matches;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Matches getMatches() {
        return matches;
    }

    public void setMatches(Matches matches) {
        this.matches = matches;
    }
}
