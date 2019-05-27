package com.stackroute.matchrecommendationservice.config;

import com.stackroute.matchrecommendationservice.domain.Matches;
import com.stackroute.matchrecommendationservice.service.RecommendationService;
import com.stackroute.rabbitmq.domain.MatchesDTO;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Consumer {

  @Autowired
  private RecommendationService recommendationServiceService;

  @RabbitListener(queues = "matches_queue")
  public void getMatchesfromMessage(MatchesDTO matchesDTO){
    recommendationServiceService.modifyCounter(getMatchObject(matchesDTO),matchesDTO.getAction());
  }

  private Matches getMatchObject(MatchesDTO matchesDTO){
    Matches matches = new Matches();
    if(matches != null){
      matches.setId(matchesDTO.getId());
      matches.setDate(matchesDTO.getDate());
      matches.setDescription(matchesDTO.getDescription());
      matches.setMatchStarted(matchesDTO.isMatchStarted());
      matches.setTeam1(matchesDTO.getTeam1());
      matches.setTeam2(matchesDTO.getTeam2());
      matches.setTitle(matchesDTO.getTitle());
      matches.setTossWinnerTeam(matchesDTO.getTossWinnerTeam());
      matches.setType(matchesDTO.getType());
      matches.setWinnerTeam(matchesDTO.getWinnerTeam());
    }
    return matches;
  }
}
