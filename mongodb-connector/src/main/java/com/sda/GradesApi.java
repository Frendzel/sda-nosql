package com.sda;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.Map;

public class GradesApi implements GradesQuery {

    private static final String COLLECTION_NAME = "grades";
    private MongoDatabase db;

    public GradesApi() {
        MongoDbConnector connector = new MongoDbConnector();
        this.db = connector.connect();
    }

    @Override
    public FindIterable<Document> find() {
        return db.getCollection(COLLECTION_NAME).find();
    }

    @Override
    public FindIterable<Document> findBy(Bson param) {
        return db.getCollection(COLLECTION_NAME).find(param);
    }

    @Override
    public void insert(Map<String, Object> document) {
        db.getCollection(COLLECTION_NAME).insertOne(new Document(document));
    }
}
