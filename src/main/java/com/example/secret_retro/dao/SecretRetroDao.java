package com.example.secret_retro.dao;

import com.example.secret_retro.model.BubbleData;
import com.example.secret_retro.model.LabelType;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

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

    @Override
    public float calculateAverageRating(LocalDate from, LocalDate to) {
        // TODO implement query
        return 3.4f;
    }

    @Override
    public List<BubbleData> getLabelsAnalytics(LocalDate from, LocalDate to) {
        // TODO implement query
        BubbleData b1 = BubbleData.builder().label("wfh").type(LabelType.BAD.toString()).value(10).build();
        BubbleData b2 = BubbleData.builder().label("spring planning").type(LabelType.GOOD.toString()).value(3).build();
        BubbleData b3 = BubbleData.builder().label("P1 bugfix").type(LabelType.GOOD.toString()).value(2).build();

        return Arrays.asList(b1, b2, b3);
    }
}
