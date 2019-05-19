package com.sda;

import org.junit.Test;
import org.testcontainers.containers.GenericContainer;

public class MySqlTestcontainersTest {

    @Test
    public void runContainer() {
        GenericContainer container = new GenericContainer("mysql:5.7")
                .withExposedPorts(3306);
        container.start();
        System.out.println(container.getContainerIpAddress() +
                container.getFirstMappedPort());
    }
}
