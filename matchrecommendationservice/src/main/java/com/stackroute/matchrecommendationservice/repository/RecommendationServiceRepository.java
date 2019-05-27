package com.stackroute.matchrecommendationservice.repository;

import com.stackroute.matchrecommendationservice.domain.Recommendation;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface RecommendationServiceRepository extends MongoRepository<Recommendation,Integer> {

}
