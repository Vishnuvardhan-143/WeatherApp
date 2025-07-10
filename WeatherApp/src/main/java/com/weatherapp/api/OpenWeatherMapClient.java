package com.weatherapp.api;

import com.weatherapp.model.WeatherData;
import com.weatherapp.parser.WeatherJsonParser;
import com.weatherapp.util.NetworkUtils;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class OpenWeatherMapClient {
    private final String baseUrl;
    private final String apiKey;
    private final WeatherCache cache;

    public OpenWeatherMapClient(String apiKey, WeatherCache cache) {
        this("https://api.openweathermap.org/data/2.5/weather", apiKey, cache);
    }

    public OpenWeatherMapClient(String baseUrl, String apiKey, WeatherCache cache) {
        this.baseUrl = baseUrl;
        this.apiKey = apiKey;
        this.cache = cache;
    }

    public WeatherData getWeatherData(String city) throws WeatherApiException {
        WeatherData cachedData = cache.get(city);
        if (cachedData != null) {
            return cachedData;
        }

        HttpURLConnection connection = null;
        try {
            String urlString = String.format("%s?q=%s&appid=%s&units=metric", baseUrl, city, apiKey);
            URL url = new URL(urlString);
            connection = (HttpURLConnection) url.openConnection();
            
            if (connection.getResponseCode() != 200) {
                throw new WeatherApiException("API request failed with code: " + connection.getResponseCode());
            }

            try (InputStream inputStream = connection.getInputStream()) {
                WeatherData weatherData = WeatherJsonParser.parse(inputStream);
                cache.put(city, weatherData);
                return weatherData;
            }
        } catch (IOException e) {
            throw new WeatherApiException("Failed to fetch weather data for " + city, e);
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }
}