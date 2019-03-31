package com.sda;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoDatabase;

import static com.mongodb.MongoCredential.createCredential;
import static com.sun.tools.javac.util.List.of;

public class MongoConnector {
    PropertyLoader propertyLoader = new PropertyLoader();

    public MongoDatabase connect() {
        propertyLoader.init();
        ServerAddress serverAddress = new ServerAddress(propertyLoader.getAddress());
        MongoCredential mongoCredential =
                createCredential(propertyLoader.getUser(),
                        propertyLoader.getDB(),
                        propertyLoader.getPassword().toCharArray());

        MongoClient mongoClient = new MongoClient(serverAddress, of(mongoCredential));
        return mongoClient.getDatabase("test");

    }
}
