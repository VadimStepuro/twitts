package com.stepuro.twitts.models;

import lombok.Data;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Data
@Component
@Scope(scopeName = "prototype")
public class Point {
    private double longitude;
    private double latitude;

    public Point(double longitude, double latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public Point() {
    }
}
