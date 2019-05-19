package com.sda;

import com.google.common.collect.ImmutableMap;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.FindIterable;
import com.mongodb.client.model.Accumulators;
import com.mongodb.client.model.Aggregates;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.json.JsonWriterSettings;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

import static com.google.common.collect.ImmutableList.of;
import static com.mongodb.client.model.Filters.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GradesServiceTest {

    private Logger logger = LoggerFactory.getLogger(GradesServiceTest.class);
    private GradesService api = new GradesService();

    @Test
    @DisplayName("Should use find method on grades collection and return all values")
    void find() {
        //when
        FindIterable<Document> documents = api.find();
        //then
        for (Document document : documents) {
            logger.info(document.toJson(
                    JsonWriterSettings
                            .builder()
                            .indent(true)
                            .build()));
        }

    }

    @Test
    @DisplayName("Should use find by method with homework param on grades collection and return all values")
    void findBy() {
        //when
        FindIterable<Document> documents = api.findBy(new Document("type", "homework"));
        //then
        for (Document document : documents) {
            logger.info(document.toJson(
                    JsonWriterSettings
                            .builder()
                            .indent(true)
                            .build()));
        }
    }

    @Test
    @DisplayName("TODO")
    void findBy2() {
        //when
        Bson filter = new Document("student_id", new Document("$gt", 20));
        Bson filter2 = gt("student_id", 20);
        FindIterable<Document> documents = api.findBy(filter);
        //then
        for (Document document : documents) {
            logger.info(document.toJson(
                    JsonWriterSettings
                            .builder()
                            .indent(true)
                            .build()));
        }
    }

    //db.grades.find({type: {$nin:["quiz","exam"]}})
    @Test
    @DisplayName("TODO")
    void findBy3() {
        //when
        Bson filter = nin("type", "quiz", "exam");
        FindIterable<Document> documents = api.findBy(filter);
        //then
        for (Document document : documents) {
            logger.info(document.toJson(
                    JsonWriterSettings
                            .builder()
                            .indent(true)
                            .build()));
        }
    }

    //db.grades.find({$and: [{type: "homework"}, {student_id: {$gt: 20}}]})
    @Test
    void findByTwoParams() {
        //when
        FindIterable<Document> documents = api.findBy(
                and(
                        eq("type", "homework"),
                        gt("student_id", 20)
                ));
        //then
        for (Document document : documents) {
            logger.info(document.toJson(JsonWriterSettings.builder().indent(true).build()));
        }
    }
    //db.grades.find({ $and: [{student_id: {$in: [1,2,3]}},
    // { $or: [{type: "exam"},{type: "quiz"}]} ]})

    //db.grades.find({ $and: [{student_id: {$in: [1,2,3]}},
    // { $or: [{type: "exam"},{type: "quiz"}]} ]}).sort({score:-1}).limit(3)

    @Test
    @DisplayName("findWithAndOrIn")
    void findBy4() {
        //when
        FindIterable<Document> documents = api.findBy(
                and(
                        in("student_id", 1, 2, 3),
                        or(
                                eq("type", "exam"),
                                eq("type", "quiz")
                        )
                )
        );
        documents.sort(new Document("score", -1)).limit(3);
        //then
        for (Document document : documents) {
            logger.info(document.toJson(
                    JsonWriterSettings.builder()
                            .indent(true)
                            .build()
            ));
        }
    }

    //insert + find
    @Test
    void insert() {
        //given
        Map<String, Object> document = ImmutableMap.of("student_id", 999, "type", "homework", "score", 111);
        //when
        api.insert(document);

        //then
        FindIterable<Document> students = api.findBy(eq("student_id", 999));
        int count = 0;
        for (Document doc : students) {
            logger.info(doc.toJson(
                    JsonWriterSettings.builder()
                            .indent(true)
                            .build()
            ));
            count++;
        }
        assertTrue(count > 0);
    }

    //db.grades.aggregate([{$match:{}},{$group:
    // { _id:"$student_id",total:{$sum: "$score"}}}])
    @Test
    void aggregateTest() {
        //given
        Bson match = Aggregates.match(new Document());
        Bson group = Aggregates.group("$student_id", Accumulators.sum("total", "$score"));
        List<Bson> bsons = of(match, group);
        //when
        AggregateIterable<Document> documents = api.aggregate(bsons);
        //then
        for (Document document : documents) {
            logger.info(document.toJson(
                    JsonWriterSettings.builder()
                            .indent(true)
                            .build()
            ));
        }
    }

    //db.grades.aggregate([{$match:{}},{$group: { _id:"$student_id",total:{$sum: "$score"}}},
    // {$sort: {total: 1}},
    // {$limit: 1}])
    @Test
    void aggregateTest2() {
        //given
        Bson match = Aggregates.match(new Document());
        Bson group = Aggregates.group("$student_id", Accumulators.sum("total", "$score"));
        Bson limit = Aggregates.limit(3);
        Bson sort = Aggregates.sort(new Document("total", 1));
        List<Bson> bsons = of(match, group, sort, limit);
        //when
        AggregateIterable<Document> documents = api.aggregate(bsons);
        //then
        for (Document document : documents) {
            logger.info(document.toJson(
                    JsonWriterSettings.builder()
                            .indent(true)
                            .build()
            ));
        }
    }
}