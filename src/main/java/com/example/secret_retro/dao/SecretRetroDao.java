package com.example.secret_retro.dao;

import org.springframework.stereotype.Repository;

@Repository
public class SecretRetroDao implements ISecretRetroDao {

    // todo mongo datasource connection setup

    @Override
    public void insertFeedback(String date, String goodLabel, String badLabel) {
        // TODO insert into feedbacks collection
    }

    @Override
    public void insertDailyRating(String date, int dailyRating) {
        // TODO insert into ratings collection
    }
}
