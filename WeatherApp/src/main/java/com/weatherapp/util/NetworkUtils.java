package com.weatherapp.util;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class NetworkUtils {
    public static boolean isNetworkAvailable() {
        try {
            URL url = new URL("http://www.google.com");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("HEAD");
            int responseCode = connection.getResponseCode();
            return (responseCode == 200);
        } catch (IOException e) {
            return false;
        }
    }
    
    public static void validateResponseCode(int code) throws IOException {
        if (code != 200) {
            throw new IOException("HTTP error code: " + code);
        }
    }
}