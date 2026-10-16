package com.agriculture.analytics.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Map;

@FeignClient(name = "IRRIGATION-SERVICE")
public interface IrrigationClient {

    @GetMapping("/api/irrigation/farm/{farmId}/latest")
    Map<String, Object> getLatestIrrigation(
            @PathVariable Long farmId);
}