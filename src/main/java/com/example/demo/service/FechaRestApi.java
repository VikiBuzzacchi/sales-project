package com.example.demo.service;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.io.IOException;
import java.time.LocalDateTime;

import com.example.demo.models.WorldClockResponse;

@Service
public class FechaRestApi {
    public LocalDateTime obtenerFecha() {
        RestTemplate restTemplate = new RestTemplate();
        final String url = "http://worldclockapi.com/api/json/utc/now";
        WorldClockResponse response = restTemplate.getForObject(url, WorldClockResponse.class);
        return response != null ? response.getCurrentDateTime() : null;
    }
}
