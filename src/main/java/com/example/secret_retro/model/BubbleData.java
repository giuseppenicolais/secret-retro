package com.example.secret_retro.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BubbleData {

    private String label;
    private String type;
    /**
     * word count
     */
    private int value;
}
