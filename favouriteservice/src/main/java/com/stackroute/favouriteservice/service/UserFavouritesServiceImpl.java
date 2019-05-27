package com.stackroute.favouriteservice.service;

import com.stackroute.favouriteservice.config.Producer;
import com.stackroute.favouriteservice.domain.Matches;
import com.stackroute.favouriteservice.domain.UserFavourites;
import com.stackroute.favouriteservice.exception.MatchAlreadyExistsException;
import com.stackroute.favouriteservice.exception.MatchNotFoundException;
import com.stackroute.favouriteservice.repository.UserFavouritesRepository;
import com.stackroute.rabbitmq.domain.MatchesDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserFavouritesServiceImpl implements UserFavouritesService {

    private UserFavouritesRepository favouritesRepository;
    private Producer producer;

    @Autowired
    public UserFavouritesServiceImpl(UserFavouritesRepository userFavouritesRepository,Producer producer){
        this.favouritesRepository = userFavouritesRepository;
        this.producer = producer;
    }

    @Override
    public Matches saveMatchToFavourites(Matches match,String userName) throws MatchAlreadyExistsException {
        UserFavourites favObj = favouritesRepository.findByUserName(userName);
        List<Matches> matchesList;
        if(favObj != null){
            matchesList = favObj.getMatchesList();
        }else {
            matchesList = new ArrayList<>();
            favObj = new UserFavourites();
            favObj.setUserName(userName);
        }

        for(Matches matchDb:matchesList){
            if(matchDb.equals(match))
                throw new MatchAlreadyExistsException();
        }
        matchesList.add(match);
        favObj.setMatchesList(matchesList);
        favouritesRepository.save(favObj);
        MatchesDTO matchesDTO = getDTOObject(match);
        matchesDTO.setAction("ADD");
        producer.sendMatchesToRabbitMQ(matchesDTO);

        return match;
    }

    @Override
    public void removeMatchFromFavourites(int id, String userName) throws MatchNotFoundException {
        boolean matchFound = false;
        UserFavourites favourites = favouritesRepository.findByUserName(userName);
        if(favourites == null || favourites.getMatchesList() == null )
            throw new MatchNotFoundException();
        List<Matches> matchesList = favourites.getMatchesList();
        for(int i=0;i < matchesList.size();i++){
            if(id == matchesList.get(i).getId()){
                matchesList.remove(i);
                matchFound = true;
                break;
            }
        }
        if(!matchFound)
            throw new MatchNotFoundException();

        favourites.setMatchesList(matchesList);
        favouritesRepository.save(favourites);
        MatchesDTO matchesDTO = new MatchesDTO();
        matchesDTO.setId(id);
        matchesDTO.setAction("REMOVE");
        producer.sendMatchesToRabbitMQ(matchesDTO);
    }

    @Override
    public List<Matches> getUserFavourites(String userName) {
        UserFavourites favourites = favouritesRepository.findByUserName(userName);
        if(favourites == null)
            favourites  = new UserFavourites();
        return favourites.getMatchesList();
    }

    private MatchesDTO getDTOObject(Matches matches){
        MatchesDTO matchesDTO = new MatchesDTO();
        if(matches != null){
            matchesDTO.setId(matches.getId());
            matchesDTO.setDate(matches.getDate());
            matchesDTO.setDescription(matches.getDescription());
            matchesDTO.setMatchStarted(matches.isMatchStarted());
            matchesDTO.setTeam1(matches.getTeam1());
            matchesDTO.setTeam2(matches.getTeam2());
            matchesDTO.setTitle(matches.getTitle());
            matchesDTO.setTossWinnerTeam(matches.getTossWinnerTeam());
            matchesDTO.setType(matches.getType());
            matchesDTO.setWinnerTeam(matches.getWinnerTeam());
        }
        return matchesDTO;
    }
}
