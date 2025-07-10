package com.weatherapp.cache;

import com.weatherapp.model.WeatherData;
import org.junit.Test;
import java.time.Duration;
import java.time.Instant;
import static org.junit.Assert.*;

public class WeatherCacheTest {
    @Test
    public void testCacheExpiry() {
        WeatherCache cache = new WeatherCache(Duration.ofMinutes(1));
        WeatherData data = new WeatherData();
        data.setCity("TestCity");
        
        cache.put("TestCity", data);
        assertNotNull(cache.get("TestCity"));
        
        // Simulate time passing (would use a mock clock in real code)
        WeatherData expiredData = new WeatherData();
        expiredData.setCity("ExpiredCity");
        expiredData.setTimestamp(Instant.now().minus(Duration.ofMinutes(2)));
        cache.put("ExpiredCity", expiredData);
        
        assertNull(cache.get("ExpiredCity"));
    }
}