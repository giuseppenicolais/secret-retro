package com.example.secret_retro.dao;

import com.example.secret_retro.model.BubbleData;
import com.example.secret_retro.model.LabelType;
import com.example.secret_retro.model.MainConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Repository
public class SecretRetroDao implements ISecretRetroDao {

    @Autowired
    private MainConfig mainConfig;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    SecretRetroDao(){
    }




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

       log.info("There are " + jdbcTemplate.queryForObject("SELECT COUNT(*) FROM secret_retro.feedbacks", Integer.class) + " records");
       return Arrays.asList(b1, b2, b3);
    }
}
