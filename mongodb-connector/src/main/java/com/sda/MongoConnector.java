package com.sda;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoDatabase;

import static com.mongodb.MongoClientOptions.builder;
import static com.mongodb.MongoCredential.createCredential;

//TODO create factory which will produce 2 connectors (local/remote)
class MongoConnector {
    private PropertyLoader propertyLoader = new PropertyLoader();

    MongoDatabase connect() {
        propertyLoader.init();
        ServerAddress serverAddress = new ServerAddress(propertyLoader.getAddress());
        MongoCredential mongoCredential =
                createCredential(propertyLoader.getUser(),
                        propertyLoader.getDB(),
                        propertyLoader.getPassword().toCharArray());

        MongoClient mongoClient = new MongoClient(serverAddress, mongoCredential, builder().build());
        return mongoClient.getDatabase(propertyLoader.getDB());
    }
}
