package org.kgsd.mbase.common;

import lombok.extern.log4j.Log4j2;
import ro.pippo.core.PippoRuntimeException;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Log4j2
public class Constants {
    public static final Properties properties = getProperties();
    // App Info
    public static final Integer APP_PORT = Integer.parseInt(properties.getProperty("mbase.port",
            "1111"));

    // Mongo
    public static final String MONGO_URL = properties.getProperty("mongo.url",
            "mongodb://localhost:27017");
    public static final String MONGO_USER_DB = "userDB";
    public static final String MONGO_USER_COLLECTION = "users";

    public static final String MONGO_USERID = "userid";
    public static final String MONGO_USERNAME = "username";
    public static final String MONGO_USERCOUNTRY = "usercountry";
    public static final String MONGO_CREATION_DATE = "userdate";

    // Request Constants
    public static final String USER_ID = "id";

    private static Properties getProperties() {
        String configFile = System.getProperty("config.path");
        Properties properties = new Properties();
        try (InputStream inputStream = configFile != null ? new FileInputStream(configFile):
                Constants.class.getClassLoader().getResourceAsStream("config.properties")) {
            assert inputStream != null;
            properties.load(inputStream);
        } catch (IOException e) {
            log.error("Loading properties file failed ", e);
            throw new PippoRuntimeException(e);
        }
        Properties systemProperties = System.getProperties();
        for (String key : systemProperties.stringPropertyNames()) {
            String value = systemProperties.getProperty(key);
            properties.setProperty(key, value);
        }
        log.info("Final properties: " +  properties);
        return properties;
    }
}
