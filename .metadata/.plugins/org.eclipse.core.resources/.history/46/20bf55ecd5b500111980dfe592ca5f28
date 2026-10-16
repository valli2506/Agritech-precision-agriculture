package com.agriculture.sensor.repository;

import com.agriculture.sensor.entity.SensorReading;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SensorReadingRepository extends JpaRepository<SensorReading, Long> {

    List<SensorReading> findBySensorId(Long sensorId);

    List<SensorReading> findByFarmId(Long farmId);

    List<SensorReading> findTop10ByFarmIdOrderByTimestampDesc(Long farmId);

    List<SensorReading> findTop10BySensorIdOrderByTimestampDesc(Long sensorId);
}