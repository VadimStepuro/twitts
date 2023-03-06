package com.stepuro.twitts.controllers;

import com.stepuro.twitts.models.Twitt;
import com.stepuro.twitts.service.TwittService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/twitts")
public class TwittController {
    @Autowired
    private TwittService service;

    @GetMapping("/getall/{path}")
    public Twitt getTwitts(@PathVariable String path){
        return service.getTwitts(path).get(0);
    }

    @GetMapping("/getsentiment/{twittsPath}/{sentimentPath}")
    public  List<Twitt> getSentimentTwitts(@PathVariable String twittsPath, @PathVariable String sentimentPath){
        return service.getSentimentTwitts(twittsPath, sentimentPath);
    }
}
