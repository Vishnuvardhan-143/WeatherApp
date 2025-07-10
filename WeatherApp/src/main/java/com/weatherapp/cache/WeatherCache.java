package com.weatherapp.cache;

import com.weatherapp.model.WeatherData;
import java.time.Duration;
import java.time.Instant;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class WeatherCache {
    private final Map<String, CacheEntry> cacheMap = new ConcurrentHashMap<>();
    private final Duration cacheDuration;

    public WeatherCache(Duration cacheDuration) {
        this.cacheDuration = cacheDuration;
    }

    public WeatherData get(String city) {
        CacheEntry entry = cacheMap.get(city.toLowerCase());
        if (entry != null && !isExpired(entry)) {
            return entry.getWeatherData();
        }
        return null;
    }

    public void put(String city, WeatherData data) {
        cacheMap.put(city.toLowerCase(), new CacheEntry(data, Instant.now()));
    }

    public void clear() {
        cacheMap.clear();
    }

    private boolean isExpired(CacheEntry entry) {
        return Duration.between(entry.getTimestamp(), Instant.now())
                      .compareTo(cacheDuration) > 0;
    }
}