package com.weBuyAnyCar.commons;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyReader {

    Properties properties = new Properties();
    InputStream inputStream = null;

    public PropertyReader() {
        loadProperties();
    }

    private void loadProperties() {

        try {
            inputStream = new FileInputStream("src/test/java/com/weBuyAnyCar/data/Data.properties");
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String readProperty(String key) {
        return properties.getProperty(key);
    }
}
