package com.stackroute.matchrecommendationservice.service;

import com.stackroute.matchrecommendationservice.domain.Matches;
import com.stackroute.matchrecommendationservice.domain.Recommendation;
import com.stackroute.matchrecommendationservice.repository.RecommendationServiceRepository;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

public class RecommendationServiceTest {

    @Mock
    private RecommendationServiceRepository repository;

    private Recommendation recommendation;
    private Matches matches;

    List rList;

    @InjectMocks
    private MatchRecommendationImpl serviceImpl;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        matches = new Matches();
        matches.setId(12345);
        matches.setDescription("Test Match");
        recommendation = new Recommendation(12345,2,matches);
        rList = new ArrayList();
        rList.add(recommendation);
    }

    @After
    public void tearDown(){
        matches = null;
        rList = null;
        recommendation = null;
    }

    @Test
    public void testGetAllMatches() throws Exception{
        when(repository.findAll(Sort.by(Sort.Direction.DESC,"count"))).thenReturn(rList);
        List recoList =serviceImpl.getRecommendationList();
        Assert.assertEquals(rList,recoList);
        verify(repository,times(1)).findAll(Sort.by(Sort.Direction.DESC,"count"));

    }


}
