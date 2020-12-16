package com.example.secret_retro.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class FeedMeBody {

    @NotNull(message = "good must not be null")
    @JsonProperty("good")
    private String labelGood;

    @JsonProperty("bad")
    @NotNull(message = "bad must not be null")
    private String labelBad;

    private String date;

    @JsonProperty("rating")
    @NotNull(message = "rating must not be null")
    private Integer dailyRating;
}
