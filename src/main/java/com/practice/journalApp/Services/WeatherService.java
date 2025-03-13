package com.practice.journalApp.Services;

import com.practice.journalApp.APIResponse.WeatherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
@Component

public class WeatherService {
    @Autowired
    private RestTemplate restTemplate;
    private final static String WEATHER_API_KEY = "7a15af33ffbbbcea08e4b4eb8e7926a3";
    private final String API_URL = "http://api.weatherstack.com/current?access_key=API_KEY&query=CITY";
    public WeatherResponse getWeather() {
        String finalURL = API_URL.replace("API_KEY", WEATHER_API_KEY)
                .replace("CITY", "New York");
        ResponseEntity<WeatherResponse> response = restTemplate.exchange(finalURL, HttpMethod.GET, null, WeatherResponse.class);
        WeatherResponse body = response.getBody();
        return body;
    }
}
