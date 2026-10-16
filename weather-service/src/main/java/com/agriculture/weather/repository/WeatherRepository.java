package com.agriculture.weather.repository;

import com.agriculture.weather.entity.WeatherData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WeatherRepository extends JpaRepository<WeatherData, Long> {

    List<WeatherData> findByFarmIdOrderByRecordedAtDesc(Long farmId);

    List<WeatherData> findTop10ByFarmIdOrderByRecordedAtDesc(Long farmId);

}