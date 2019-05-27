package com.stackroute.matchrecommendationservice.service;

import com.stackroute.matchrecommendationservice.domain.Matches;
import com.stackroute.matchrecommendationservice.domain.Recommendation;
import com.stackroute.matchrecommendationservice.repository.RecommendationServiceRepository;
import com.stackroute.rabbitmq.domain.MatchesDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MatchRecommendationImpl implements RecommendationService {

    private RecommendationServiceRepository repository;

    @Autowired
    public MatchRecommendationImpl (RecommendationServiceRepository repository){
        this.repository = repository;
    }
    @Override
    public void modifyCounter(Matches matches, String action) {
        Recommendation recommendation;
        int count;
        Optional<Recommendation> opt = repository.findById(matches.getId());
        if(opt.isPresent()){
            recommendation = opt.get();
            count = recommendation.getCount();
        }else{
            count =0;
            recommendation = new Recommendation(matches.getId(),count,matches);
        }
        if("ADD".equals(action))
            count++;
        else if("REMOVE".equals(action)){
            if(count > 0)
                count --;
        }
        recommendation.setCount(count);
        repository.save(recommendation);

    }

    @Override
    public List<Recommendation> getRecommendationList() {
        return repository.findAll(Sort.by(Sort.Direction.DESC,"count"));
    }
}
