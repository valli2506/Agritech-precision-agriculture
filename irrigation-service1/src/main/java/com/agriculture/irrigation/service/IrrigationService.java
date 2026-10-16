package com.agriculture.irrigation.service;

import com.agriculture.irrigation.client.CropClient;
import com.agriculture.irrigation.client.SensorClient;
import com.agriculture.irrigation.client.WeatherClient;
import com.agriculture.irrigation.entity.IrrigationRecommendation;
import com.agriculture.irrigation.repository.IrrigationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class IrrigationService {

    private final IrrigationRepository irrigationRepository;
    private final SensorClient sensorClient;
    private final WeatherClient weatherClient;
    private final CropClient cropClient;

    public IrrigationService(
            IrrigationRepository irrigationRepository,
            SensorClient sensorClient,
            WeatherClient weatherClient,
            CropClient cropClient) {

        this.irrigationRepository = irrigationRepository;
        this.sensorClient = sensorClient;
        this.weatherClient = weatherClient;
        this.cropClient = cropClient;
    }

    public IrrigationRecommendation generateRecommendation(
            Long farmId) {

        List<Map<String, Object>> readings =
                sensorClient.getLatestReadings(farmId);

        Map<String, Object> weather =
                weatherClient.getLatestWeather(farmId);

        List<Map<String, Object>> crops =
                cropClient.getCropsByFarm(farmId);

        if (readings.isEmpty()) {
            throw new RuntimeException(
                    "No sensor readings found for farm: " + farmId);
        }

        if (crops.isEmpty()) {
            throw new RuntimeException(
                    "No crop found for farm: " + farmId);
        }

        double soilMoisture = 0.0;

        for (Map<String, Object> reading : readings) {

            String unit = String.valueOf(
                    reading.get("unit")
            );

            if ("%".equals(unit)) {
                soilMoisture =
                        ((Number) reading.get("value"))
                                .doubleValue();

                break;
            }
        }

        double rainfall =
                ((Number) weather.get("rainfall"))
                        .doubleValue();

        Map<String, Object> crop = crops.get(0);

        Long cropId =
                ((Number) crop.get("id")).longValue();

        double requiredMoisture =
                ((Number) crop.get(
                        "requiredSoilMoisture"))
                        .doubleValue();

        IrrigationRecommendation recommendation =
                new IrrigationRecommendation();

        recommendation.setFarmId(farmId);
        recommendation.setCropId(cropId);
        recommendation.setSoilMoisture(soilMoisture);
        recommendation.setRainfall(rainfall);

        /*
         * SMART IRRIGATION LOGIC
         */

        if (rainfall >= 10) {

            recommendation.setRecommendation(
                    "Heavy rainfall detected. Delay irrigation."
            );

            recommendation.setIrrigationStatus(
                    "DELAY_IRRIGATION"
            );

            recommendation.setRecommendedAmount(0.0);

        } else if (soilMoisture < requiredMoisture
                && rainfall < 5) {

            recommendation.setRecommendation(
                    "Soil moisture is low. Irrigation required."
            );

            recommendation.setIrrigationStatus(
                    "IRRIGATION_REQUIRED"
            );

            recommendation.setRecommendedAmount(
                    calculateWaterAmount(
                            requiredMoisture,
                            soilMoisture
                    )
            );

        } else {

            recommendation.setRecommendation(
                    "Soil moisture is sufficient. No irrigation required."
            );

            recommendation.setIrrigationStatus(
                    "NO_IRRIGATION_REQUIRED"
            );

            recommendation.setRecommendedAmount(0.0);
        }

        return irrigationRepository.save(
                recommendation
        );
    }

    private double calculateWaterAmount(
            double requiredMoisture,
            double currentMoisture) {

        double deficit =
                requiredMoisture - currentMoisture;

        if (deficit <= 0) {
            return 0.0;
        }

        return deficit * 10;
    }

    public List<IrrigationRecommendation>
    getFarmRecommendations(Long farmId) {

        return irrigationRepository
                .findByFarmIdOrderByCreatedAtDesc(farmId);
    }

    public IrrigationRecommendation
    getLatestRecommendation(Long farmId) {

        List<IrrigationRecommendation> list =
                irrigationRepository
                        .findByFarmIdOrderByCreatedAtDesc(
                                farmId
                        );

        if (list.isEmpty()) {
            throw new RuntimeException(
                    "No irrigation recommendation found"
            );
        }

        return list.get(0);
    }
}