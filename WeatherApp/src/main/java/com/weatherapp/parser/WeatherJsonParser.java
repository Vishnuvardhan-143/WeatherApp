package com.weatherapp.parser;

import com.weatherapp.model.WeatherData;
import javax.json.Json;
import javax.json.stream.JsonParser;
import javax.json.stream.JsonParser.Event;
import java.io.InputStream;
import java.time.Instant;

public class WeatherJsonParser {
    public static WeatherData parse(InputStream inputStream) {
        WeatherData data = new WeatherData();
        try (JsonParser parser = Json.createParser(inputStream)) {
            String currentKey = null;
            String currentObject = null;
            
            while (parser.hasNext()) {
                Event event = parser.next();
                switch (event) {
                    case KEY_NAME:
                        currentKey = parser.getString();
                        break;
                    case VALUE_STRING:
                        if ("name".equals(currentKey)) {
                            data.setCity(parser.getString());
                        } else if ("description".equals(currentKey) && "weather".equals(currentObject)) {
                            data.setDescription(parser.getString());
                        }
                        break;
                    case VALUE_NUMBER:
                        if ("temp".equals(currentKey) && "main".equals(currentObject)) {
                            data.setTemperature(parser.getBigDecimal().doubleValue());
                        } else if ("humidity".equals(currentKey) && "main".equals(currentObject)) {
                            data.setHumidity(parser.getInt());
                        } else if ("pressure".equals(currentKey) && "main".equals(currentObject)) {
                            data.setPressure(parser.getBigDecimal().doubleValue());
                        } else if ("speed".equals(currentKey) && "wind".equals(currentObject)) {
                            data.setWindSpeed(parser.getBigDecimal().doubleValue());
                        }
                        break;
                    case START_OBJECT:
                        if (currentKey != null) {
                            currentObject = currentKey;
                        }
                        break;
                    case END_OBJECT:
                        currentObject = null;
                        break;
                    default:
                        break;
                }
            }
        }
        data.setTimestamp(Instant.now());
        return data;
    }
}