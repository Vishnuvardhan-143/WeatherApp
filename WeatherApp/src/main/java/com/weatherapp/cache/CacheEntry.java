package com.weatherapp.cache;

import com.weatherapp.model.WeatherData;
import java.time.Instant;

public class CacheEntry {
    private final WeatherData weatherData;
    private final Instant timestamp;

    public CacheEntry(WeatherData weatherData, Instant timestamp) {
        this.weatherData = weatherData;
        this.timestamp = timestamp;
    }

    public WeatherData getWeatherData() {
        return weatherData;
    }

    public Instant getTimestamp() {
        return timestamp;
    }
}