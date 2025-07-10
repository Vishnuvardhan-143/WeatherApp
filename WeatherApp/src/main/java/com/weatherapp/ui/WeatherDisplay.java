package com.weatherapp.ui;

import com.weatherapp.model.WeatherData;

public interface WeatherDisplay {
    void displayWeather(WeatherData data);
    void displayError(String message);
}