package com.stackroute.favouriteservice.repository;

import com.stackroute.favouriteservice.domain.Matches;
import com.stackroute.favouriteservice.domain.UserFavourites;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@DataMongoTest
public class FavouritesRepoTest {

    @Autowired
    private UserFavouritesRepository repository;
    private Matches matches;
    private UserFavourites userFavourites;

    @Before
    public void setUp(){
        matches = new Matches();
        matches.setId(12345);
        matches.setDescription("Test Match");
        List<Matches> matchesList = new ArrayList<>();
        matchesList.add(matches);
        userFavourites = new UserFavourites("Thyagu",matchesList);
    }

    @After
    public void tearDown(){
        matches = null;
        userFavourites = null;
        repository.deleteAll();
    }


    @Test
    public void saveMatchesTest(){
        repository.save(userFavourites);
        UserFavourites fetchUser = repository.findByUserName(userFavourites.getUserName());
        List<Matches> dbMatchesList = fetchUser.getMatchesList();
        Assert.assertEquals(dbMatchesList.get(0).getId(),userFavourites.getMatchesList().get(0).getId());

    }


}
