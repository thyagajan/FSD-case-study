package com.stackroute.matchrecommendationservice.controller;

import com.stackroute.matchrecommendationservice.service.RecommendationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@RequestMapping("api/v1/recommendationservice")
public class RecommendationController {

    private RecommendationService recommendationService;
    private ResponseEntity responseEntity;

    @Autowired
    public RecommendationController(RecommendationService recommendationService){
        this.recommendationService = recommendationService;
    }

    @GetMapping("/recommendations")
    public ResponseEntity getRecommendations(){
        return new ResponseEntity(recommendationService.getRecommendationList(), HttpStatus.OK);
    }
}
