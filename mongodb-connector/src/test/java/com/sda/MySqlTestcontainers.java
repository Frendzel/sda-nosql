package com.sda;

import org.junit.Test;
import org.testcontainers.containers.GenericContainer;

public class MySqlTestcontainers {

    @Test
    public void test() {
        GenericContainer genericContainer = new GenericContainer("mysql:5.7");
        genericContainer.start();
        System.out.println(genericContainer.getContainerInfo());
    }
}
