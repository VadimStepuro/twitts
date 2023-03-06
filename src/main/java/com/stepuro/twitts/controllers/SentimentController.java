package com.stepuro.twitts.controllers;

import com.stepuro.twitts.models.Sentiment;
import com.stepuro.twitts.service.SentimentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/sentiments")
public class SentimentController {

    @Autowired
    SentimentService sentimentService;

    @GetMapping("/getall/{path}")
    public List<Sentiment> getAllSentiments(@PathVariable String path){
        return sentimentService.getSentiments(path);
    }
}
