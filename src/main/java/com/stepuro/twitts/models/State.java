package com.stepuro.twitts.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.stepuro.twitts.Utils.Polygon2D;
import lombok.Data;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;



@Data
@Component
@Scope(scopeName = "prototype")
public class State {
    @JsonIgnore
    private List<Polygon2D> coordinates;
    private String name;
    @JsonIgnore
    private List<Twitt> twitts = new ArrayList<>();
    @JsonIgnore
    private boolean isEmotional;
    private double sentiment;



    public State(String name, List<Polygon2D> coordinates) {
        this.coordinates = coordinates;
        this.name = name;
    }
}
