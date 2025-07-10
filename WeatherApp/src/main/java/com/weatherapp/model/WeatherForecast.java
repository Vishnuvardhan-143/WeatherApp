package com.weatherapp.model;

import java.util.List;

public class WeatherForecast {
    private String city;
    private List<WeatherData> forecastData;
    
    public String getCity() {
        return city;
    }
    
    public void setCity(String city) {
        this.city = city;
    }
    
    public List<WeatherData> getForecastData() {
        return forecastData;
    }
    
    public void setForecastData(List<WeatherData> forecastData) {
        this.forecastData = forecastData;
    }
}