package com.sda;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MongoConnectorTest {

    private PropertyLoader propertyLoader;


    @Before
    public void setUp() {
        propertyLoader = new PropertyLoader();
        propertyLoader.init();
    }

    @Test
    public void connect() {
        assertEquals("test", propertyLoader.getUser());
    }

    @Test
    public void mongoConnector() {
        //given
        MongoConnector mongoConnector = new MongoConnector();
        //when
        MongoDatabase db = mongoConnector.connect();
        //then
        MongoCollection<Document> grades = db.getCollection("grades");
        assertEquals(grades.estimatedDocumentCount(), 804);
    }
}