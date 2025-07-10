package com.weatherapp.parser;

import com.weatherapp.model.WeatherData;
import org.junit.Test;
import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import static org.junit.Assert.*;

public class WeatherJsonParserTest {
    @Test
    public void testParseValidJson() {
        String json = "{" +
            "\"name\":\"London\"," +
            "\"main\":{" +
                "\"temp\":15.5," +
                "\"humidity\":65," +
                "\"pressure\":1012.5" +
            "}," +
            "\"wind\":{" +
                "\"speed\":3.2" +
            "}," +
            "\"weather\":[{" +
                "\"description\":\"scattered clouds\"" +
            "}]" +
        "}";
        
        ByteArrayInputStream input = new ByteArrayInputStream(json.getBytes(StandardCharsets.UTF_8));
        WeatherData data = WeatherJsonParser.parse(input);
        
        assertEquals("London", data.getCity());
        assertEquals(15.5, data.getTemperature(), 0.001);
        assertEquals(65, data.getHumidity());
        assertEquals(1012.5, data.getPressure(), 0.001);
        assertEquals(3.2, data.getWindSpeed(), 0.001);
        assertEquals("scattered clouds", data.getDescription());
    }
}