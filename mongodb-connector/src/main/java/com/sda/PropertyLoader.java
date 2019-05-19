package com.sda;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyLoader {

    private Logger logger = LoggerFactory.getLogger(PropertyLoader.class);
    private Properties properties = new Properties();

    public PropertyLoader(){
        init();
    }

    private void init() {
        logger.debug("Starting initialization process.");
        try (InputStream stream = this
                .getClass()
                .getClassLoader()
                .getResourceAsStream("connection.properties")
        ) {
            logger.trace("Before loading properties.");
            this.properties.load(stream);
            logger.debug("Properties has been loaded.");
        } catch (IOException e) {
            logger.error("OMG💩", e);
        }
        logger.info("All properties has been loaded correctly.");
    }

    String getUser() {
        return getProperty("user");
    }

    String getPassword() {
        return getProperty("password");
    }

    String getDB() {
        return getProperty("db");
    }

    String getAddress() {
        return getProperty("address");
    }

    String getUri() {
        return getProperty("uri");
    }

    private String getProperty(String property) {
        return properties.getProperty(property);
    }

}
