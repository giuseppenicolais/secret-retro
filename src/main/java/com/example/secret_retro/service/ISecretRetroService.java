package com.example.secret_retro.service;

import com.example.secret_retro.model.FeedMeBody;
import com.example.secret_retro.model.Feedback;

public interface ISecretRetroService {

    void feedMe(FeedMeBody payload);

    Feedback getLastMonthAnalytics();
}
