package com.agriculture.weather.controller;

import com.agriculture.weather.entity.WeatherData;
import com.agriculture.weather.service.WeatherService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/weather")
public class WeatherController {

    private final WeatherService weatherService;

    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @PostMapping
    public ResponseEntity<WeatherData> addWeather(
            @RequestBody WeatherData weatherData) {

        return new ResponseEntity<>(
                weatherService.addWeatherData(weatherData),
                HttpStatus.CREATED
        );
    }

    @GetMapping("/farm/{farmId}")
    public ResponseEntity<List<WeatherData>> getWeatherByFarm(
            @PathVariable Long farmId) {

        return ResponseEntity.ok(
                weatherService.getWeatherByFarm(farmId)
        );
    }

    @GetMapping("/farm/{farmId}/latest")
    public ResponseEntity<WeatherData> getLatestWeather(
            @PathVariable Long farmId) {

        return ResponseEntity.ok(
                weatherService.getLatestWeather(farmId)
        );
    }

    @GetMapping("/farm/{farmId}/history")
    public ResponseEntity<List<WeatherData>> getWeatherHistory(
            @PathVariable Long farmId) {

        return ResponseEntity.ok(
                weatherService.getWeatherHistory(farmId)
        );
    }
}