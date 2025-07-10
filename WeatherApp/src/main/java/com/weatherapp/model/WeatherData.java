package com.weatherapp.model;

import java.time.Instant;
import java.util.Objects;

public class WeatherData {
    private double temperature;
    private int humidity;
    private double pressure;
    private double windSpeed;
    private String description;
    private String city;
    private Instant timestamp;
    
    // Getters and setters
    public double getTemperature() { return temperature; }
    public void setTemperature(double temperature) { this.temperature = temperature; }
    
    public int getHumidity() { return humidity; }
    public void setHumidity(int humidity) { this.humidity = humidity; }
    
    public double getPressure() { return pressure; }
    public void setPressure(double pressure) { this.pressure = pressure; }
    
    public double getWindSpeed() { return windSpeed; }
    public void setWindSpeed(double windSpeed) { this.windSpeed = windSpeed; }
    
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    
    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }
    
    public Instant getTimestamp() { return timestamp; }
    public void setTimestamp(Instant timestamp) { this.timestamp = timestamp; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WeatherData that = (WeatherData) o;
        return Double.compare(that.temperature, temperature) == 0 &&
                humidity == that.humidity &&
                Double.compare(that.pressure, pressure) == 0 &&
                Double.compare(that.windSpeed, windSpeed) == 0 &&
                Objects.equals(description, that.description) &&
                Objects.equals(city, that.city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(temperature, humidity, pressure, windSpeed, description, city);
    }
}