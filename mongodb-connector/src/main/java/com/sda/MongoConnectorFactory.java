package com.sda;

import static com.sda.ConnectionType.LOCAL;
import static com.sda.ConnectionType.REMOTE;

public class MongoConnectorFactory {

    public MongoConnector connectToMongoDB(ConnectionType type) {
        if (LOCAL == type) {
            return new LocalMongoConnector();
        } else if (REMOTE == type) {
            return new RemoteMongoConnector();
        } else {
            throw new RuntimeException();
        }
    }

}
