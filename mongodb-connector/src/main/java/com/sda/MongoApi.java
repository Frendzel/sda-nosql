package com.sda;

import com.mongodb.client.MongoDatabase;

public interface MongoApi {
    MongoDatabase connect();
    void close();
}
