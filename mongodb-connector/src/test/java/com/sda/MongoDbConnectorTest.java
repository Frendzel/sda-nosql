package com.sda;

import com.mongodb.client.MongoDatabase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MongoDbConnectorTest {

    @Test
    @DisplayName("Should correctly connect to default database.")
    void should_correctly_connect_to_default_database() {
        //given
        MongoDbConnector connector = new MongoDbConnector();
        //when
        MongoDatabase connect = connector.connect();
        //then
        assertEquals(connect.getName(), connector.getPropertyLoader().getDB());
    }
}