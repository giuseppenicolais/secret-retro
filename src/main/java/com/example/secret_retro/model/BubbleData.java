package com.example.secret_retro.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BubbleData {

    private String label;
    private String type;
    /**
     * word count
     */
    private int value;
}
