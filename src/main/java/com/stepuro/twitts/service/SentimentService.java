package com.stepuro.twitts.service;

import com.stepuro.twitts.models.Sentiment;
import com.stepuro.twitts.repositories.SentimentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SentimentService {
    @Autowired
    private SentimentRepository sentimentRepository;

    public List<Sentiment> getSentiments(String path){
        return sentimentRepository.read(path);
    }
}
