package com.example.secret_retro.service;

import com.example.secret_retro.model.DailyRating;
import com.example.secret_retro.model.FeedMeBody;
import com.example.secret_retro.model.Feedback;

import java.util.List;

public interface ISecretRetroService {

    void feedMe(FeedMeBody payload);

    Feedback getLastMonthAnalytics();

    List<DailyRating> getLastMonthRating();
}
