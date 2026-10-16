package com.agriculture.analytics.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Map;

@FeignClient(name = "CROP-SERVICE")
public interface CropClient {

    @GetMapping("/api/crops/farm/{farmId}")
    List<Map<String, Object>> getCropsByFarm(
            @PathVariable Long farmId);
}