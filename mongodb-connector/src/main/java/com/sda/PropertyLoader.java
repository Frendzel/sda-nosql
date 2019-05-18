package com.sda;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

//TODO logger
public class PropertyLoader {

    private Properties properties = new Properties();

    public void init() {
        try (InputStream stream = this
                .getClass()
                .getClassLoader()
                .getResourceAsStream("connection.properties")
        ) {
            properties.load(stream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
