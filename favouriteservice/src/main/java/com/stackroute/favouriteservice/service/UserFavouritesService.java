package com.stackroute.favouriteservice.service;

import com.stackroute.favouriteservice.domain.Matches;
import com.stackroute.favouriteservice.domain.UserFavourites;
import com.stackroute.favouriteservice.exception.MatchAlreadyExistsException;
import com.stackroute.favouriteservice.exception.MatchNotFoundException;


import java.util.List;

public interface UserFavouritesService {
    Matches saveMatchToFavourites(Matches match, String userName) throws MatchAlreadyExistsException;
    void removeMatchFromFavourites(int id, String userName) throws MatchNotFoundException;
    List<Matches> getUserFavourites(String userName);

}
