package com.sda;

import org.junit.Rule;
import org.testcontainers.containers.GenericContainer;

public class TestcontainerTest {

    @Rule
    public GenericContainer mongo = new GenericContainer<>("mongodb:4.0.0");

    public void initTest() {
        System.out.println(mongo.getContainerIpAddress());
    }

}
