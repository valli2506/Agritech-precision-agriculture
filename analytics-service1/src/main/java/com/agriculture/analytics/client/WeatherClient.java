package com.agriculture.analytics.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Map;

@FeignClient(name = "WEATHER-SERVICE")
public interface WeatherClient {

    @GetMapping("/api/weather/farm/{farmId}/latest")
    Map<String, Object> getLatestWeather(
            @PathVariable Long farmId);
}