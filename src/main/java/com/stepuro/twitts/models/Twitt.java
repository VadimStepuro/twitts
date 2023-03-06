package com.stepuro.twitts.models;

import lombok.Builder;
import lombok.Data;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;


@Component
@Scope(scopeName = "prototype")
@Data
@Builder
public class Twitt {
    private double latitude;
    private double longitude;
    private LocalDateTime time;
    private String text;
    private double sentiment;
    private boolean isEmotional;
}
