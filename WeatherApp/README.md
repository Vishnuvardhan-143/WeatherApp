# Weather Application

A Java-based weather application that fetches and displays real-time data from the OpenWeatherMap API.

![Weather App Screenshot](screenshot.png) *(optional: add screenshot later)*

## Features

- Real-time weather data fetching
- Efficient JSON parsing
- Configurable caching system
- Swing-based GUI
- Comprehensive error handling

## Setup Instructions

1. **Prerequisites**:
   - Java 8 or higher
   - Maven (for building)

2. **Configuration**:
   - Create `apikey.txt` in `src/main/resources/` with your OpenWeatherMap API key
   - Example content:
     ```
     cd83649fe4f9407fdd238460ffa04dff
     ```

3. **Build & Run**:
   ```bash
   mvn clean package
   java -jar target/WeatherApp.jar