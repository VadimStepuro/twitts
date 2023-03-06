package com.stepuro.twitts.repositories;

import com.stepuro.twitts.Utils.SentimentComparer;
import com.stepuro.twitts.models.Sentiment;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

@Component
public class SentimentRepository implements RepositoryInterface<Sentiment> {

    public List<Sentiment> read(String path) {
        File myObj = new File("D://springBlog//twitts//src//main//resources//templates", path);
        List<Sentiment> sentiments = new ArrayList<>();

        Scanner myReader;
        try {
            myReader = new Scanner(myObj);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        while (myReader.hasNextLine()) {
            String data = myReader.nextLine();
            String[] strings = data.split(",");
            sentiments.add(new Sentiment(strings[0], Double.parseDouble(strings[1])));
        }
        myReader.close();
        sentiments.sort(new SentimentComparer());
        return sentiments;
    }
}