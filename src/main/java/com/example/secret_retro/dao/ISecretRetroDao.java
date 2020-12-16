package com.example.secret_retro.dao;

import com.example.secret_retro.model.BubbleData;

import java.time.LocalDate;
import java.util.List;

public interface ISecretRetroDao {
    void insertFeedback(String date, String type, String label);

    void insertDailyRating(String date, int dailyRating);

    float calculateAverageRating(LocalDate from, LocalDate to);

    List<BubbleData> getLabelsAnalytics(LocalDate from, LocalDate to);
}
