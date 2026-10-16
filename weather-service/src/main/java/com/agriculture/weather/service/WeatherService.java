package com.agriculture.weather.service;

import com.agriculture.weather.entity.WeatherData;
import com.agriculture.weather.repository.WeatherRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WeatherService {

    private final WeatherRepository weatherRepository;

    public WeatherService(WeatherRepository weatherRepository) {
        this.weatherRepository = weatherRepository;
    }

    public WeatherData addWeatherData(WeatherData weatherData) {
        return weatherRepository.save(weatherData);
    }

    public List<WeatherData> getWeatherByFarm(Long farmId) {
        return weatherRepository.findByFarmIdOrderByRecordedAtDesc(farmId);
    }

    public WeatherData getLatestWeather(Long farmId) {

        List<WeatherData> data =
                weatherRepository.findTop10ByFarmIdOrderByRecordedAtDesc(farmId);

        if (data.isEmpty()) {
            throw new RuntimeException(
                    "No weather data found for farm: " + farmId
            );
        }

        return data.get(0);
    }

    public List<WeatherData> getWeatherHistory(Long farmId) {
        return weatherRepository.findByFarmIdOrderByRecordedAtDesc(farmId);
    }
}