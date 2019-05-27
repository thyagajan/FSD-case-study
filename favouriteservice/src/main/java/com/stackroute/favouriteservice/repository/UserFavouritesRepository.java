package com.stackroute.favouriteservice.repository;

import com.stackroute.favouriteservice.domain.UserFavourites;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserFavouritesRepository extends MongoRepository<UserFavourites,String> {

    UserFavourites findByUserName(String userName);
}
