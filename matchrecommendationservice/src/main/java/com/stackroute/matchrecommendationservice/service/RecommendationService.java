package com.stackroute.matchrecommendationservice.service;

import com.stackroute.matchrecommendationservice.domain.Matches;
import com.stackroute.matchrecommendationservice.domain.Recommendation;

import java.util.List;

public interface RecommendationService {
    void modifyCounter(Matches matches, String action);
    List<Recommendation> getRecommendationList();
}
