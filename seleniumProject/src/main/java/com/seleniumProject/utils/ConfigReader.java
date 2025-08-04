package com.seleniumProject.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    private Properties properties = new Properties();

    public ConfigReader() {
        try (FileInputStream fis = new FileInputStream("src/main/resources/config.properties")) {
            properties.load(fis);
        } catch (IOException e) {
            throw new RuntimeException("config.properties okunamadı: " + e.getMessage());
        }
    }

    public String get(String key) {
        String value = properties.getProperty(key);
        if (value != null) return value;
        else throw new RuntimeException(key + " değeri config.properties içinde tanımlı değil");
    }

    public String getBaseUrl() {
        return get("baseUrl");
    }

    public String getBrowserHeadless() {
        return get("headless");
    }

}
