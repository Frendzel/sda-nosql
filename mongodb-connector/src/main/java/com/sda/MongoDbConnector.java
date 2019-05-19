package com.sda;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoDatabase;

import static com.mongodb.MongoCredential.createCredential;

public class MongoDbConnector implements MongoApi {

    private MongoClient mongoClient;
    private PropertyLoader propertyLoader = new PropertyLoader();

    @Override
    public MongoDatabase connect() {
        createClient();
        return mongoClient.getDatabase(propertyLoader.getDB());
    }

    @Override
    public void close() {
        mongoClient.close();
    }

    private void createClient() {
        ServerAddress serverAddress = new ServerAddress(propertyLoader.getAddress());
        MongoCredential credential = createCredential(
                propertyLoader.getUser(),
                propertyLoader.getDB(),
                propertyLoader.getPassword().toCharArray());
        MongoClientOptions options = new MongoClientOptions.Builder().build();
        mongoClient = new MongoClient(serverAddress, credential, options);
    }

    public PropertyLoader getPropertyLoader() {
        return propertyLoader;
    }

}
