package com.stackroute.matchrecommendationservice.repository;

import com.stackroute.matchrecommendationservice.domain.Matches;
import com.stackroute.matchrecommendationservice.domain.Recommendation;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@DataMongoTest
public class RecommendationRepoTest {

    @Autowired
    private RecommendationServiceRepository repository;
    private Recommendation recommendation;
    private Matches matches;


    @Before
    public void setUp(){
        matches = new Matches();
        matches.setId(12345);
        matches.setDescription("Test Match");
        recommendation = new Recommendation(12345,2,matches);
    }

    @After
    public void tearDown(){
        matches = null;
        recommendation = null;
        repository.deleteAll();
    }

    @Test
    public void saveMatchesTest(){
        repository.save(recommendation);
        Recommendation recommendationObj = repository.findById(12345).get();
        Assert.assertEquals(recommendationObj.getMatches(),recommendation.getMatches());

    }

}
