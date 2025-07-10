package com.weatherapp.util;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public class ConfigManager {
    private final Properties properties;
    
    public ConfigManager() throws IOException {
        properties = new Properties();
        try (InputStream input = Files.newInputStream(Paths.get("src/main/resources/config.properties"))) {
            properties.load(input);
        }
    }
    
    public String getApiKey() throws IOException {
        return new String(Files.readAllBytes(Paths.get("src/main/resources/apikey.txt"))).trim();
    }
    
    public int getCacheDurationMinutes() {
        return Integer.parseInt(properties.getProperty("cache.duration.minutes", "30"));
    }
    
    public String getApiBaseUrl() {
        return properties.getProperty("api.base.url");
    }
}