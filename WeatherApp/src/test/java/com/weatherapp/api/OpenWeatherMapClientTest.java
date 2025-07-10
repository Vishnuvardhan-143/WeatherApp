package com.weatherapp.api;

import com.weatherapp.cache.WeatherCache;
import com.weatherapp.model.WeatherData;
import org.junit.Before;
import org.junit.Test;
import java.time.Duration;
import static org.junit.Assert.*;

public class OpenWeatherMapClientTest {
    private OpenWeatherMapClient client;
    private WeatherCache cache;
    
    @Before
    public void setUp() {
        cache = new WeatherCache(Duration.ofMinutes(30));
        client = new OpenWeatherMapClient("test_api_key", cache);
    }
    
    @Test(expected = WeatherApiException.class)
    public void testInvalidApiKey() throws WeatherApiException {
        client.getWeatherData("London");
    }
    
    @Test
    public void testCacheIntegration() throws WeatherApiException {
        // Mock test - in real tests you would use a mock API
        WeatherData testData = new WeatherData();
        testData.setCity("TestCity");
        cache.put("TestCity", testData);
        
        WeatherData result = client.getWeatherData("TestCity");
        assertEquals("TestCity", result.getCity());
    }
}