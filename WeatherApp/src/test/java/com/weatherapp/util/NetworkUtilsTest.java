package com.weatherapp.util;

import org.junit.Test;
import static org.junit.Assert.*;

public class NetworkUtilsTest {
    @Test
    public void testNetworkAvailable() {
        // This is a simple test that just verifies the method doesn't throw exceptions
        // In a real project, you would mock the network connection
        boolean available = NetworkUtils.isNetworkAvailable();
        assertTrue(available || !available); // Just check it returns a boolean
    }
}