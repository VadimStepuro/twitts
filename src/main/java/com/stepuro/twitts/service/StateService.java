package com.stepuro.twitts.service;

import com.stepuro.twitts.Utils.Polygon2D;
import com.stepuro.twitts.models.State;
import com.stepuro.twitts.models.Twitt;
import com.stepuro.twitts.repositories.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StateService {
    @Autowired
    private StateRepository stateRepository;

    @Autowired
    private TwittService twittService;
    public List<State> getStates(String statesPath, String twittPath, String sentimentPath){
        List<State> states = stateRepository.read("");
        List<Twitt> twitts = twittService.getSentimentTwitts(twittPath, sentimentPath);
        states.parallelStream().forEach(state -> {
            twitts.parallelStream().forEach(twitt -> {
                for (Polygon2D polygon : state.getCoordinates()) {
                    if (polygon.contains(twitt.getLatitude(), twitt.getLongitude())) {
                        state.getTwitts().add(twitt);
                        state.setSentiment(state.getSentiment() + twitt.getSentiment());
                        if (twitt.isEmotional())
                            state.setEmotional(true);
                    }
                }
            });
            state.setSentiment(state.getSentiment() / state.getTwitts().size());
        });

        return states;
    }
}
