package com.stepuro.twitts.repositories;

import com.stepuro.twitts.models.Twitt;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Component
public class TwittRepository implements RepositoryInterface<Twitt> {

    public List<Twitt> read(String path) {
        File myObj = new File("D://springBlog//twitts//src//main//resources//templates", path);
        List<Twitt> twitts = new ArrayList<>();
        Scanner myReader;
        try {
            myReader = new Scanner(myObj);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        while (myReader.hasNextLine()) {
            String data = myReader.nextLine();
            if(data.startsWith("[")){
                String[] strings = data.split("\t");
                twitts.add(parseTwitt(strings));
            }
        }
        myReader.close();
        return twitts;
    }

    private double[] getLocation(String twitt){
        String str = twitt.substring(1, twitt.length()-1);
        String[] splitted = str.split(", ");
        double[] locations = new double[2];
        locations[0] = Double.parseDouble(splitted[0]);
        locations[1] = Double.parseDouble(splitted[1]);
        return locations;
    }

    private LocalDateTime getTime(String twitt){
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return LocalDateTime.parse(twitt, timeFormatter);
    }

    private Twitt parseTwitt(String[] strings){
        double[] location = getLocation(strings[0]);
        LocalDateTime time = getTime(strings[2]);
        return Twitt.builder()
                .latitude(location[1])
                .longitude(location[0])
                .time(time)
                .text(strings[3])
                .build();
    }

}
