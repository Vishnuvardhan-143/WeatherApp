package com.weatherapp;

import com.weatherapp.api.OpenWeatherMapClient;
import com.weatherapp.cache.WeatherCache;
import com.weatherapp.model.WeatherData;
import com.weatherapp.ui.MainWindow;
import com.weatherapp.util.ConfigManager;
import java.time.Duration;

public class WeatherApp {
    public static void main(String[] args) {
        try {
            ConfigManager config = new ConfigManager();
            String apiKey = config.getApiKey();
            Duration cacheDuration = Duration.ofMinutes(config.getCacheDurationMinutes());
            
            WeatherCache cache = new WeatherCache(cacheDuration);
            OpenWeatherMapClient weatherClient = new OpenWeatherMapClient(apiKey, cache);
            
            MainWindow window = new MainWindow(weatherClient);
            window.show();
        } catch (Exception e) {
            System.err.println("Failed to start application: " + e.getMessage());
            e.printStackTrace();
        }
    }
}