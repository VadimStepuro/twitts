package com.stepuro.twitts.models;

import lombok.Data;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Data
@Component
@Scope(scopeName = "prototype")
public class Sentiment {
    private String text;
    private Double weight;

    public Sentiment(){}

    public Sentiment(String text, Double weight) {
        this.text = text;
        this.weight = weight;
    }
}
