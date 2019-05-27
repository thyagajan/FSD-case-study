package com.stackroute.favouriteservice.service;

import com.stackroute.favouriteservice.config.Producer;
import com.stackroute.favouriteservice.domain.Matches;
import com.stackroute.favouriteservice.domain.UserFavourites;
import com.stackroute.favouriteservice.exception.MatchAlreadyExistsException;
import com.stackroute.favouriteservice.exception.MatchNotFoundException;
import com.stackroute.favouriteservice.repository.UserFavouritesRepository;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;


public class FavouriteServiceTest {

    @Mock
    private UserFavouritesRepository repository;

    @Mock
    private Producer producer;

    private Matches matches;
    private UserFavourites userFavourites;


    @InjectMocks
    private UserFavouritesServiceImpl serviceImpl;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
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
    public void testSaveMatchSucess() throws MatchAlreadyExistsException {
        //when(repository.findByUserName(userFavourites.getUserName())).thenReturn(userFavourites);
        when(repository.save(userFavourites)).thenReturn(userFavourites);
        Matches matchesdb = serviceImpl.saveMatchToFavourites(matches,userFavourites.getUserName());
        Assert.assertEquals(matches,matchesdb);


    }



    @Test
    public void testDeleteMatch() throws MatchNotFoundException {
        when(repository.findByUserName(userFavourites.getUserName())).thenReturn(userFavourites);
        serviceImpl.removeMatchFromFavourites(matches.getId(),userFavourites.getUserName());
        verify(repository,times(1)).save(userFavourites);
        verify(repository,times(1)).findByUserName(userFavourites.getUserName());

    }

    @Test
    public void testGetAllMatches() throws Exception{
        when(repository.findByUserName(userFavourites.getUserName())).thenReturn(userFavourites);
        List<Matches> matchesList =serviceImpl.getUserFavourites(userFavourites.getUserName());
        Assert.assertEquals(matchesList,userFavourites.getMatchesList());
        verify(repository,times(1)).findByUserName(userFavourites.getUserName());

    }
}
