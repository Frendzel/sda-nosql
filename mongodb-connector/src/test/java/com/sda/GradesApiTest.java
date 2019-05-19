package com.sda;

import com.mongodb.client.FindIterable;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.json.JsonWriterSettings;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.mongodb.client.model.Filters.gt;

class GradesApiTest {

    private Logger logger = LoggerFactory.getLogger(GradesApiTest.class);
    private GradesApi api = new GradesApi();

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
}