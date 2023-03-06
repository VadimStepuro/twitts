package com.stepuro.twitts.controllers;

import com.stepuro.twitts.models.State;
import com.stepuro.twitts.models.Twitt;
import com.stepuro.twitts.service.StateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/states")
public class StateController {
    @Autowired
    StateService stateService;

    @GetMapping("/getall/{statePath}/{twittPath}/{sentimentPath}")
    public List<State> getAllStates(@PathVariable String statePath, @PathVariable String twittPath, @PathVariable String sentimentPath){
        return stateService.getStates(statePath, twittPath, sentimentPath);
    }

    @GetMapping("/getalltwitts/{statePath}/{twittPath}/{sentimentPath}")
    public List<Twitt> getAllTwitts(@PathVariable String statePath, @PathVariable String twittPath, @PathVariable String sentimentPath){
        return stateService.getStates(statePath, twittPath, sentimentPath).get(0).getTwitts();
    }
}
