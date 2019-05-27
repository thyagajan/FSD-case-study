package com.stackroute.favouriteservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.favouriteservice.domain.Matches;
import com.stackroute.favouriteservice.domain.UserFavourites;
import com.stackroute.favouriteservice.service.UserFavouritesService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(UserFavouritesController.class)
public class FavouritesControllerTest {

    @Autowired
    private MockMvc mockmvc;

    @MockBean
    private UserFavouritesService userFavouritesService;

    private Matches matches;
    private UserFavourites userFavourites;

    @Before
    public void setUp(){

        matches = new Matches();
        matches.setId(123);
        matches.setDescription("Test Match");
        List<Matches> matchesList = new ArrayList<>();
        matchesList.add(matches);
        userFavourites = new UserFavourites("Thyagu",matchesList);
    }

    @After
    public void tearDown(){
        matches = null;
        userFavourites = null;
    }

    @Test
    public void saveMatchestoFavorutiresSuccess() throws Exception {
        when(userFavouritesService.saveMatchToFavourites(any(),eq(userFavourites.getUserName()))).thenReturn(matches);
        mockmvc.perform(post("/api/v1/userfavourites/users/{userName}/match",userFavourites.getUserName())
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonToString(matches)))
                .andExpect(status().isCreated())
                .andDo(print());
        verify(userFavouritesService,times(1)).saveMatchToFavourites(any(),eq(userFavourites.getUserName()));

    }

    @Test
    public void getTracksFromWishList() throws Exception {
        when(userFavouritesService.getUserFavourites(userFavourites.getUserName())).thenReturn(userFavourites.getMatchesList());
        mockmvc.perform(get("/api/v1/userfavourites/users/{userName}/matches",userFavourites.getUserName())
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonToString(matches)))
                .andExpect(status().isOk())
                .andDo(print());
        verify(userFavouritesService,times(1)).getUserFavourites(userFavourites.getUserName());
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
