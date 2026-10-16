package com.agriculture.analytics.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Map;

@FeignClient(name = "SENSOR-SERVICE")
public interface SensorClient {

    @GetMapping("/api/sensors/farm/{farmId}/latest")
    List<Map<String, Object>> getLatestReadings(
            @PathVariable Long farmId);
}