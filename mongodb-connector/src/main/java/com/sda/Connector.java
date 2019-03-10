package com.sda;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoDatabase;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Connector {

    private MongoClient mongoClient;
    private PropertyLoader propertyLoader = new PropertyLoader();

    public Connector() throws IOException {
        this.propertyLoader.init();
    }

    /**
     * user=test
     * password=test123
     * address=127.0.0.1
     * db=test
     * @return
     */
    public MongoDatabase connect() {
        ServerAddress serverAddress = new ServerAddress(propertyLoader.getAddress());
        MongoCredential credential = MongoCredential.createCredential(
                propertyLoader.getUser(),
                propertyLoader.getDb(),
                propertyLoader.getPassword().toCharArray());
        List<MongoCredential> credentialList = new ArrayList<MongoCredential>();
        credentialList.add(credential);
        mongoClient = new MongoClient(serverAddress, credentialList);
        return mongoClient.getDatabase(propertyLoader.getDb());
    }
}
