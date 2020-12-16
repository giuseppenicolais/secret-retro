package com.example.secret_retro.dao;

public interface ISecretRetroDao {
    void insertFeedback(String date, String type, String label);

    void insertDailyRating(String date, int dailyRating);

}
