package com.example.secret_retro.service;

import com.example.secret_retro.dao.SecretRetroDao;
import com.example.secret_retro.model.BubbleData;
import com.example.secret_retro.model.DailyRating;
import com.example.secret_retro.model.FeedMeBody;
import com.example.secret_retro.model.Feedback;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;


@Service
@Slf4j
public class SecretRetroService implements ISecretRetroService {

    @Autowired
    private SecretRetroDao secretRetroDao;

    @Override
    public void feedMe(FeedMeBody payload) {

        int minusDay = (int) (Math.random() * 30 + 1);
        LocalDate randomMonthlyDate = LocalDate.now().minus(minusDay, ChronoUnit.DAYS);
        String date = DateTimeFormatter.ISO_LOCAL_DATE.format(randomMonthlyDate);
        String badLabel = payload.getLabelBad().toLowerCase().trim();
        String goodLabel = payload.getLabelGood().toLowerCase().trim();

        log.info("Feeding the database with good: {} - bad: {} - rating: {} - date: {}", payload.getLabelGood(), payload.getLabelBad(), payload.getDailyRating(), date);
        secretRetroDao.insertFeedback(date, goodLabel, badLabel, payload.getDailyRating());
    }

    /**
     * Pull the data to feed the bubble chart for the last month
     */
    @Override
    public Feedback getLastMonthAnalytics() {

        LocalDate to = LocalDate.now();
        LocalDate from = to.minus(30, ChronoUnit.DAYS);

        String lastMonth = calculateLastMonthDateRange(from, to);

        List<BubbleData> bubbleDataList = secretRetroDao.getLabelsAnalytics(from, to);
        float avgRating = secretRetroDao.calculateAverageRating(from, to);

        return Feedback.builder()
                .avgRating(avgRating)
                .dateRange(lastMonth)
                .labels(bubbleDataList).build();
    }

    @Override
    public List<DailyRating> getLastMonthRating() {
        LocalDate to = LocalDate.now();
        LocalDate from = to.minus(30, ChronoUnit.DAYS);

        return secretRetroDao.getLastMonthRating(from, to);
    }

    private String calculateLastMonthDateRange(LocalDate from, LocalDate to) {
        return DateTimeFormatter.ISO_LOCAL_DATE.format(from) + " to " + DateTimeFormatter.ISO_LOCAL_DATE.format(to);
    }
}
