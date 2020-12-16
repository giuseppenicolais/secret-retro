package com.example.secret_retro.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DailyRating {

    @JsonProperty("created_at")
    private String createdAt;
    @JsonProperty("rating")
    private float dailyRating;

}
