package com.example.secret_retro.service;

import com.example.secret_retro.dao.SecretRetroDao;
import com.example.secret_retro.model.FeedMeBody;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


@Service
@Slf4j
public class SecretRetroService implements ISecretRetroService {

    @Autowired
    private SecretRetroDao secretRetroDao;

    @Override
    public void feedMe(FeedMeBody payload) {

        String date = payload.getDate();
        if(StringUtils.isBlank(payload.getDate())){
            date = DateTimeFormatter.ISO_LOCAL_DATE.format(LocalDate.now());
        }

        String badLabel = payload.getLabelBad().toLowerCase();
        String goodLabel = payload.getLabelGood().toLowerCase();
        //TODO any transformations ??

        // TODO transactional with mongo ?

        log.info("Feeding the database with good: {} - bad: {} - rating: {} - date: {}", payload.getLabelGood(), payload.getLabelBad(), payload.getDailyRating(), date);
        secretRetroDao.insertFeedback(date, goodLabel, badLabel);
        secretRetroDao.insertDailyRating(date, payload.getDailyRating());
    }
}
