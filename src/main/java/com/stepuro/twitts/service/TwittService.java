package com.stepuro.twitts.service;

import com.stepuro.twitts.models.Sentiment;
import com.stepuro.twitts.models.Twitt;
import com.stepuro.twitts.repositories.SentimentRepository;
import com.stepuro.twitts.repositories.TwittRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TwittService {
    @Autowired
    private TwittRepository repository;

    @Autowired
    private SentimentRepository sentimentRepository;

    public List<Twitt> getTwitts(String path){
        return repository.read(path);
    }

    public List<Twitt> getSentimentTwitts(String twittsPath, String sentimentPath){
        List<Twitt> twitts = getTwitts(twittsPath);
        List<Sentiment> sentiments = sentimentRepository.read(sentimentPath);

        twitts.parallelStream().forEach( twitt -> {
            int index;
            clearText(twitt);
            StringBuilder twittText = new StringBuilder(twitt.getText());
            for (Sentiment sentiment:sentiments) {
                index = twittText.indexOf(sentiment.getText());

                while(index != -1){
                    if(!twitt.isEmotional())
                        twitt.setEmotional(true);
                    twitt.setSentiment(twitt.getSentiment() + sentiment.getWeight());
                    twittText.delete(index, index + sentiment.getText().length());
                    index = twittText.indexOf(sentiment.getText());
                }
            }
        });
        return twitts;
    }

    private void clearText(Twitt twitt){
        twitt.setText(removeUrl(twitt.getText()));
        twitt.setText(removeNick(twitt.getText()));
        twitt.setText(removeHashTag(twitt.getText()));
        twitt.setText(removePunctuation(twitt.getText()));
    }

    private String removeUrl(String comment){
        return comment.replaceAll("((https?|ftp|gopher|telnet|file|Unsure|http):((//)|(\\\\))+[\\w:#@%/;$()~_?+-=\\\\.&]*)", "").trim();
    }

    private String removeNick(String comment){
        return comment.replaceAll("@\\w+", "").trim();
    }

    private String removeHashTag(String comment){
        return comment.replaceAll("#\\w+", "").trim();
    }

    private String removePunctuation(String comment){return comment.replaceAll("\\p{Punct}", "");}

}
