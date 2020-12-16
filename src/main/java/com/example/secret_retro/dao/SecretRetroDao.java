package com.example.secret_retro.dao;

import com.example.secret_retro.model.BubbleData;
import com.example.secret_retro.model.LabelType;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.Block;
import com.mongodb.client.*;
import lombok.extern.slf4j.Slf4j;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Repository
public class SecretRetroDao implements ISecretRetroDao {

    private static final String QUERY_AVERAGE = "[\n" +
            "  {\n" +
            "    \"$match\": {\n" +
            "      \"created_at\": {\n" +
            "        \"$nin\": [\n" +
            "          null,\n" +
            "          \"\"\n" +
            "        ]\n" +
            "      }\n" +
            "    }\n" +
            "  },\n" +
            "  {\n" +
            "    \"$group\": {\n" +
            "      \"_id\": {},\n" +
            "      \"__alias_0\": {\n" +
            "        \"$avg\": \"$rating\"\n" +
            "      }\n" +
            "    }\n" +
            "  },\n" +
            "  {\n" +
            "    \"$project\": {\n" +
            "      \"_id\": 0,\n" +
            "      \"__alias_0\": 1\n" +
            "    }\n" +
            "  },\n" +
            "  {\n" +
            "    \"$project\": {\n" +
            "      \"x\": \"$__alias_0\",\n" +
            "      \"_id\": 0\n" +
            "    }\n" +
            "  },\n" +
            "  {\n" +
            "    \"$limit\": 5000\n" +
            "  }\n" +
            "]";
    private MongoDatabase database;
    private MongoCollection feedbacksCollection;

    SecretRetroDao(){
        MongoClient mongoClient = MongoClients.create(
                "mongodb+srv://secret-retro:secret-retro@cluster0.cstc5.mongodb.net/secretretro?retryWrites=true&w=majority");
        database = mongoClient.getDatabase("secretretro");
        feedbacksCollection = database.getCollection("feedbacks");
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
        log.info("There are " + feedbacksCollection.countDocuments()+ " documents ");
        List<Bson> averageMetricQuery = null;
        try {
            averageMetricQuery = (List<Bson>) new ObjectMapper().readValue(QUERY_AVERAGE, List.class);
        } catch (JsonProcessingException e) {
            log.error(e.getMessage(), e);
        }
        //database.getCollection("feedbacks").aggregate(averageMetricQuery).forEach((Block<? super Document>) item->log.info(item.toJson()));
        return Arrays.asList(b1, b2, b3);
    }
}
