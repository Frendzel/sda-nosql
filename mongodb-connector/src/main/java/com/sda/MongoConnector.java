package com.sda;

import com.mongodb.client.MongoDatabase;

@FunctionalInterface
public interface MongoConnector {

    MongoDatabase connect();
}
