package com.example.secret_retro.dao;

import com.example.secret_retro.model.BubbleData;
import com.example.secret_retro.model.MainConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

import java.util.List;

@Slf4j
@Repository
public class SecretRetroDao implements ISecretRetroDao {

    private static final String INSERT_FEEDBACK = "INSERT INTO secret_retro.feedbacks (bad, good, rating) VALUES(?, ?, ?);";
    @Autowired
    private MainConfig mainConfig;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    SecretRetroDao(){
    }


BeanPropertyRowMapper<BubbleData> bubbleDataBeanPropertyRowMapper = BeanPropertyRowMapper.newInstance(BubbleData.class);

    @Override
    public void insertFeedback(String date, String goodLabel, String badLabel, int rating) {
        jdbcTemplate.update(INSERT_FEEDBACK, badLabel, goodLabel, rating);
        log.info("Feedback successfully inserted");
    }

    @Override
    public float calculateAverageRating(LocalDate from, LocalDate to) {
        // TODO implement query
        return jdbcTemplate.queryForObject("select avg(rating) from secret_retro.feedbacks where created_at between " +
                "to_date('"+from.toString()+"','YYYY-MM-DD') and to_date('"+to.toString()+"','YYYY-MM-DD')", Float.class);

    }

    @Override
    public List<BubbleData> getLabelsAnalytics(LocalDate from, LocalDate to) {
        // TODO implement query
        //BubbleData b1 = BubbleData.builder().label("wfh").type(LabelType.BAD.toString()).value(10).build();
        //BubbleData b2 = BubbleData.builder().label("spring planning").type(LabelType.GOOD.toString()).value(3).build();
       //BubbleData b3 = BubbleData.builder().label("P1 bugfix").type(LabelType.GOOD.toString()).value(2).build();

        List<BubbleData> bubbleDataList = jdbcTemplate.query( "select count(bad) as value, bad as label, 'BAD' as type from secret_retro.feedbacks group by bad",
                bubbleDataBeanPropertyRowMapper);
        bubbleDataList.addAll(jdbcTemplate.query( "select count(good) as value, good as label, 'GOOD' as type from secret_retro.feedbacks group by good",
                bubbleDataBeanPropertyRowMapper));

       log.info("There are " + jdbcTemplate.queryForObject("SELECT COUNT(*) FROM secret_retro.feedbacks", Integer.class) + " records");
       return bubbleDataList;
    }
}
