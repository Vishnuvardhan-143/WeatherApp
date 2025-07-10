package com.weatherapp.api;

import com.weatherapp.model.WeatherData;

public class WeatherApiResponse {
    private WeatherData data;
    private String errorMessage;
    
    public WeatherApiResponse(WeatherData data) {
        this.data = data;
    }
    
    public WeatherApiResponse(String errorMessage) {
        this.errorMessage = errorMessage;
    }
    
    public boolean isSuccess() {
        return data != null;
    }
    
    public WeatherData getData() {
        return data;
    }
    
    public String getErrorMessage() {
        return errorMessage;
    }
}