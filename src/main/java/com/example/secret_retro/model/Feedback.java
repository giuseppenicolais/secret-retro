package com.example.secret_retro.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class Feedback {

    @JsonProperty("avg_rating")
    private float avgRating;
    @JsonProperty("date_range")
    private String dateRange;
    private List<BubbleData> labels;
}
