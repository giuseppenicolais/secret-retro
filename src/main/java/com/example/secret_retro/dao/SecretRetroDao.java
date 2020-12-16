package com.example.secret_retro.dao;

import com.example.secret_retro.model.BubbleData;
import com.example.secret_retro.model.DailyRating;
import com.example.secret_retro.model.MainConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Repository
public class SecretRetroDao implements ISecretRetroDao {

    private static final String INSERT_FEEDBACK = "INSERT INTO secret_retro.feedbacks (created_at, bad, good, rating) VALUES(?, ?, ?, ?);";
    private static final String SELECT_DAILY_RATINGS = "SELECT created_at, avg(rating) as rating FROM secret_retro.feedbacks where created_at >= :from and created_at <= :to " +
            " group by(created_at)" +
            " order by created_at asc";

    @Autowired
    private MainConfig mainConfig;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    SecretRetroDao(){
    }


    BeanPropertyRowMapper<BubbleData> bubbleDataBeanPropertyRowMapper = BeanPropertyRowMapper.newInstance(BubbleData.class);

    @Override
    public void insertFeedback(String date, String goodLabel, String badLabel, int rating) {
        jdbcTemplate.update(INSERT_FEEDBACK, Date.valueOf(date), badLabel, goodLabel, rating);
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
        List<BubbleData> bubbleDataList = jdbcTemplate.query( "select count(bad) as value, bad as label, 'BAD' as type from secret_retro.feedbacks group by bad",
                bubbleDataBeanPropertyRowMapper);
        bubbleDataList.addAll(jdbcTemplate.query( "select count(good) as value, good as label, 'GOOD' as type from secret_retro.feedbacks group by good",
                bubbleDataBeanPropertyRowMapper));

       log.info("There are " + jdbcTemplate.queryForObject("SELECT COUNT(*) FROM secret_retro.feedbacks", Integer.class) + " records");
       return bubbleDataList;
    }

    public List<DailyRating> getLastMonthRating(LocalDate from, LocalDate to) {

        Map<String, Object> params = new HashMap<>();
        params.put("from", java.util.Date.from(from.atStartOfDay(ZoneId.systemDefault()).toInstant()));
        params.put("to",java.util.Date.from(to.atStartOfDay(ZoneId.systemDefault()).toInstant()));

        return namedParameterJdbcTemplate.query(SELECT_DAILY_RATINGS, new MapSqlParameterSource(params), (resultSet, i) -> {
                        Date date = resultSet.getDate("created_at");
        int rating = resultSet.getInt("rating");
        String _date = new SimpleDateFormat("yyyy-MM-dd").format(date);
        return DailyRating.builder().dailyRating(rating).createdAt(_date).build();
        });
    }
}
