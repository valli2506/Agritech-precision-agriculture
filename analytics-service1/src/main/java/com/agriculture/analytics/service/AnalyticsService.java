package com.agriculture.analytics.service;

import com.agriculture.analytics.client.CropClient;
import com.agriculture.analytics.client.IrrigationClient;
import com.agriculture.analytics.client.SensorClient;
import com.agriculture.analytics.client.WeatherClient;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AnalyticsService {

    private final SensorClient sensorClient;
    private final WeatherClient weatherClient;
    private final CropClient cropClient;
    private final IrrigationClient irrigationClient;

    public AnalyticsService(
            SensorClient sensorClient,
            WeatherClient weatherClient,
            CropClient cropClient,
            IrrigationClient irrigationClient) {

        this.sensorClient = sensorClient;
        this.weatherClient = weatherClient;
        this.cropClient = cropClient;
        this.irrigationClient = irrigationClient;
    }

    public Map<String, Object> getDashboard(Long farmId) {

        Map<String, Object> dashboard = new HashMap<>();

        dashboard.put("farmId", farmId);

        dashboard.put(
                "sensorReadings",
                sensorClient.getLatestReadings(farmId)
        );

        dashboard.put(
                "weather",
                weatherClient.getLatestWeather(farmId)
        );

        dashboard.put(
                "crops",
                cropClient.getCropsByFarm(farmId)
        );

        dashboard.put(
                "irrigation",
                irrigationClient.getLatestIrrigation(farmId)
        );

        return dashboard;
    }

    public List<Map<String, Object>> getSoilData(Long farmId) {

        return sensorClient.getLatestReadings(farmId);
    }

    public Map<String, Object> getWeatherData(Long farmId) {

        return weatherClient.getLatestWeather(farmId);
    }

    public Map<String, Object> getIrrigationData(Long farmId) {

        return irrigationClient.getLatestIrrigation(farmId);
    }

    public List<Map<String, Object>> getCropData(Long farmId) {

        return cropClient.getCropsByFarm(farmId);
    }

    public Map<String, Object> getSummary(Long farmId) {

        Map<String, Object> summary = new HashMap<>();

        summary.put("farmId", farmId);
        summary.put("soilData",
                sensorClient.getLatestReadings(farmId));

        summary.put("weather",
                weatherClient.getLatestWeather(farmId));

        summary.put("crops",
                cropClient.getCropsByFarm(farmId));

        summary.put("irrigation",
                irrigationClient.getLatestIrrigation(farmId));

        return summary;
    }
}