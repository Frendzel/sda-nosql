package com.sda;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class RemoteMongoConnector {

    PropertyLoader propertyLoader = new PropertyLoader();

    public MongoDatabase connect() {
        MongoClientURI uri = new MongoClientURI(propertyLoader.getUri());
        MongoClient mongoClient = new MongoClient(uri);
        return mongoClient.getDatabase("test");
    }

    public static void main(String[] args) {
        RemoteMongoConnector remoteMongoConnector = new RemoteMongoConnector();
        MongoDatabase connect = remoteMongoConnector.connect();
        MongoCollection<Document> grades = connect.getCollection("grades");
        System.out.println(grades.countDocuments());
    }
}
