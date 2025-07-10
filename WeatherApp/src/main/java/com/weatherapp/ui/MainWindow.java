package com.weatherapp.ui;

import com.weatherapp.api.OpenWeatherMapClient;
import com.weatherapp.api.WeatherApiException;
import com.weatherapp.model.WeatherData;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainWindow extends JFrame {
    private final OpenWeatherMapClient weatherClient;
    private JTextField cityField;
    private JTextArea weatherDisplay;
    
    public MainWindow(OpenWeatherMapClient weatherClient) {
        this.weatherClient = weatherClient;
        initializeUI();
    }
    
    private void initializeUI() {
        setTitle("Weather Application");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        
        // Input panel
        JPanel inputPanel = new JPanel();
        cityField = new JTextField(20);
        JButton fetchButton = new JButton("Get Weather");
        
        fetchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fetchWeather();
            }
        });
        
        inputPanel.add(new JLabel("City:"));
        inputPanel.add(cityField);
        inputPanel.add(fetchButton);
        
        // Display panel
        weatherDisplay = new JTextArea();
        weatherDisplay.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(weatherDisplay);
        
        add(inputPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
    }
    
    private void fetchWeather() {
        String city = cityField.getText().trim();
        if (city.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a city name", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        try {
            WeatherData data = weatherClient.getWeatherData(city);
            displayWeather(data);
        } catch (WeatherApiException e) {
            weatherDisplay.setText("Error fetching weather data: " + e.getMessage());
        }
    }
    
    private void displayWeather(WeatherData data) {
        String weatherInfo = String.format(
            "Weather for %s:\n" +
            "Temperature: %.1f°C\n" +
            "Humidity: %d%%\n" +
            "Pressure: %.1f hPa\n" +
            "Wind Speed: %.1f m/s\n" +
            "Conditions: %s\n" +
            "Last updated: %s",
            data.getCity(),
            data.getTemperature(),
            data.getHumidity(),
            data.getPressure(),
            data.getWindSpeed(),
            data.getDescription(),
            data.getTimestamp()
        );
        weatherDisplay.setText(weatherInfo);
    }
    
    public void show() {
        setVisible(true);
    }
}