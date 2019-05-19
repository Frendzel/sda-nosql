package com.sda;

import com.mongodb.client.FindIterable;
import org.bson.Document;
import org.bson.json.JsonWriterSettings;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class GradesApiTest {

    private Logger logger = LoggerFactory.getLogger(GradesApiTest.class);

    @Test
    @DisplayName("Should use find method on grades collection and return all values")
    void find() {
        //given
        GradesApi api = new GradesApi();
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
//    @Test
//    void findBy(){
//        //given
//        GradesApi api = new GradesApi();
//        //when
//        FindIterable<Document> documents = api.
//    }
}