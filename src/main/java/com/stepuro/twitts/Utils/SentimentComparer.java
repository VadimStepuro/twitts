package com.stepuro.twitts.Utils;

import com.stepuro.twitts.models.Sentiment;

import java.util.Comparator;

public class SentimentComparer implements Comparator<Sentiment> {
    @Override
    public int compare(Sentiment o1, Sentiment o2) {
        return o2.getText().length() - o1.getText().length();
    }
}
