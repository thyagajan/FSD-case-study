package com.stackroute.favouriteservice.controller;

import com.stackroute.favouriteservice.domain.Matches;
import com.stackroute.favouriteservice.domain.UserFavourites;
import com.stackroute.favouriteservice.exception.MatchAlreadyExistsException;
import com.stackroute.favouriteservice.exception.MatchNotFoundException;
import com.stackroute.favouriteservice.service.UserFavouritesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/userfavourites")
public class UserFavouritesController {

    private UserFavouritesService favouritesService;
    private ResponseEntity responseEntity;

    @Autowired
    public UserFavouritesController(UserFavouritesService favouritesService){
        this.favouritesService = favouritesService;
    }

    @PostMapping("/users/{userName}/match")
    public ResponseEntity saveMatchesToFavourites(@RequestBody Matches match, @PathVariable("userName") String userName) throws MatchAlreadyExistsException{
        try{
            Matches matches = favouritesService.saveMatchToFavourites(match,userName);
            responseEntity = new ResponseEntity(matches,HttpStatus.CREATED);
        }catch(MatchAlreadyExistsException maee){
            throw maee;
        }catch(Exception e){
            e.printStackTrace();
            responseEntity = new ResponseEntity("Try after Some time", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @DeleteMapping("/users/{userName}/match/{id}")
    public ResponseEntity removeMatchesFromFavourites(@PathVariable("userName") String userName, @PathVariable("id") int id) throws MatchNotFoundException {
        try{
            favouritesService.removeMatchFromFavourites(id,userName);
            responseEntity = new ResponseEntity("Removed Successfully.",HttpStatus.OK);
        }catch(MatchNotFoundException mnfe){
            throw mnfe;
        }catch(Exception e){
            e.printStackTrace();
            responseEntity = new ResponseEntity("Error occured. Please try after sometime.",HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @GetMapping("users/{userName}/matches")
    public ResponseEntity getUserFavourites(@PathVariable("userName") String userName){
        try{
            List<Matches> matchesList = favouritesService.getUserFavourites(userName);
            responseEntity = new ResponseEntity(matchesList,HttpStatus.OK);
        }catch(Exception e){
            responseEntity = new ResponseEntity("Error occured. Please try after sometime.",HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }
}
