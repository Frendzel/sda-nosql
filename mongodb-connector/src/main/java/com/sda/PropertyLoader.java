package com.sda;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyLoader {

    private static PropertyLoader instance;
    private Properties properties = new Properties();

    public PropertyLoader() {
    }

    public static PropertyLoader getInstance() {
        synchronized (PropertyLoader.class) {
            if (instance == null) {
                instance = new PropertyLoader();
            }
            return instance;
        }
    }


    public void init() {
        try (InputStream stream = this.getClass().getClassLoader().getResourceAsStream("connection.properties")) {
            properties.load(stream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getUser() {
        return getData("user");
    }

    public String getPassword() {
        return getData("password");
    }

    public String getAddress() {
        return getData("address");
    }

    public String getDB() {
        return getData("db");
    }

    public String getURI() {
        return getData("uri");
    }

    private String getData(String data) {
        return properties.getProperty(data);
    }

}
