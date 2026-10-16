package com.agriculture.analytics.controller;

import com.agriculture.analytics.service.AnalyticsService;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/analytics")
public class AnalyticsController {

    private final AnalyticsService analyticsService;

    public AnalyticsController(AnalyticsService analyticsService) {
        this.analyticsService = analyticsService;
    }

    @GetMapping("/farm/{farmId}/dashboard")
    public Map<String, Object> getDashboard(
            @PathVariable Long farmId) {

        return analyticsService.getDashboard(farmId);
    }

    @GetMapping("/farm/{farmId}/soil")
    public List<Map<String, Object>> getSoil(
            @PathVariable Long farmId) {

        return analyticsService.getSoilData(farmId);
    }

    @GetMapping("/farm/{farmId}/weather")
    public Map<String, Object> getWeather(
            @PathVariable Long farmId) {

        return analyticsService.getWeatherData(farmId);
    }

    @GetMapping("/farm/{farmId}/irrigation")
    public Map<String, Object> getIrrigation(
            @PathVariable Long farmId) {

        return analyticsService.getIrrigationData(farmId);
    }

    @GetMapping("/farm/{farmId}/summary")
    public Map<String, Object> getSummary(
            @PathVariable Long farmId) {

        return analyticsService.getSummary(farmId);
    }
}