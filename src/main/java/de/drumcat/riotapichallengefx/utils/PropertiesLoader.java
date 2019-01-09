package de.drumcat.riotapichallengefx.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesLoader {

    public static Properties loadProperties(String resourceFileName){
        Properties configuration = new Properties();
        InputStream inputStream = PropertiesLoader.class
                .getClassLoader()
                .getResourceAsStream(resourceFileName);
        try {
            configuration.load(inputStream);
            inputStream.close();
        } catch (IOException e) {
            return configuration;
        }
        return configuration;
    }
}