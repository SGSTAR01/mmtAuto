package com.subham.utils;

import java.io.FileInputStream;
import java.util.Properties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ConfigUtil {
    private static final Logger logger = LogManager.getLogger(ConfigUtil.class);

    public static String loadConfig(String key) {
        try {
            logger.info("Loading config property: {}", key);
            FileInputStream fis = new FileInputStream("src/test/resources/config.properties");
            Properties properties = new Properties();
            properties.load(fis);
            String value = properties.getProperty(key);
            if (value == null) {
                String errorMsg = "Property key '" + key +"' not found in config.properties";
                logger.error(errorMsg);
                throw new RuntimeException(errorMsg);
            }
            logger.info("Loaded value for key '{}': {}", key, value);
            return value;
        } catch (Exception e) {
            logger.error("Error loading config property: {}", key, e);
            throw new RuntimeException(e);
        }
    }

}
