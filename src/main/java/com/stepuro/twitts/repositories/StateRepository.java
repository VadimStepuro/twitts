package com.stepuro.twitts.repositories;

import com.stepuro.twitts.Utils.Polygon2D;
import com.stepuro.twitts.models.State;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Component;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
public class StateRepository implements RepositoryInterface<State>{
    @Override
    public List<State> read(String path) {
        JSONParser parser = new JSONParser();

        try (Reader reader = new FileReader("D://springBlog//twitts//src//main//resources//templates//states.json")) {
            List<State> states = new ArrayList<>();
            JSONObject jsonObject = (JSONObject) parser.parse(reader);
            for (Object o : jsonObject.keySet()) {
                String key = (String) o;
                List<Polygon2D> p = getPolygons((JSONArray) jsonObject.get(key));
                states.add(new State(key, p));
            }
            return states;

        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }
    }

    private List<Polygon2D> getPolygons(JSONArray jsonArray){
        List<Polygon2D> polygons = new ArrayList<>();
        for (JSONArray array : (Iterable<JSONArray>) jsonArray) {
            polygons.add(getPoints(array));
        }
        return polygons;
    }

    private Polygon2D getPoints(JSONArray pointsArray){
        List<Double> pointsx = new ArrayList<>();
        List<Double> pointsy = new ArrayList<>();

        Iterator<JSONArray> jsonArrayIterator = pointsArray.iterator();
        if(pointsArray.size() == 1)
            jsonArrayIterator = ((JSONArray) pointsArray.iterator().next()).iterator();
        while (jsonArrayIterator.hasNext()){
            Iterator<Double> iterator = jsonArrayIterator.next().iterator();
            pointsx.add(iterator.next());
            pointsy.add(iterator.next());
        }
        double[] pointx = pointsx.stream()
                .mapToDouble(Double::doubleValue)
                .toArray();
        double[] pointy = pointsy.stream()
                .mapToDouble(Double::doubleValue)
                .toArray();
        return new Polygon2D(pointx, pointy, pointsx.size());
    }

}
