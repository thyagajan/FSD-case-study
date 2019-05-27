package com.stackroute.matchrecommendationservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.matchrecommendationservice.domain.Matches;
import com.stackroute.matchrecommendationservice.domain.Recommendation;
import com.stackroute.matchrecommendationservice.service.RecommendationService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(RecommendationController.class)
public class RecommendationControllerTest {
    @Autowired
    private MockMvc mockmvc;

    @MockBean
    private RecommendationService service;

    private Recommendation recommendation;
    private Matches matches;
    private List rList;


    @Before
    public void setUp(){
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
        recommendation = null;
    }

    @Test
    public void getRecommendationList() throws Exception {
        when(service.getRecommendationList()).thenReturn(rList);
        mockmvc.perform(get("/api/v1/recommendationservice/recommendations")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonToString(matches)))
                .andExpect(status().isOk())
                .andDo(print());
        verify(service,times(1)).getRecommendationList();
    }


    private static String jsonToString(final Object obj) throws JsonProcessingException {
        String result ;
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            result = objectMapper.writeValueAsString(obj);

        }catch(Exception e){
            result = "Error Processing JSON";
        }
        return result;
    }
}
