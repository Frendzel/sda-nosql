package com.sda;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;

import static com.sda.PropertyLoader.getInstance;

public class RemoteMongoConnector implements MongoConnector {

    private PropertyLoader propertyLoader = getInstance();

    @Override
    public MongoDatabase connect() {
        propertyLoader.init();
        MongoClientURI uri = new MongoClientURI(propertyLoader.getURI());
        MongoClient mongoClient = new MongoClient(uri);
        return mongoClient.getDatabase(propertyLoader.getDB());
    }
}
