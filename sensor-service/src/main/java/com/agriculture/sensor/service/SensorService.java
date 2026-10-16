package com.agriculture.sensor.service;

import com.agriculture.sensor.entity.Sensor;
import com.agriculture.sensor.entity.SensorReading;
import com.agriculture.sensor.repository.SensorReadingRepository;
import com.agriculture.sensor.repository.SensorRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SensorService {

    private final SensorRepository sensorRepository;
    private final SensorReadingRepository readingRepository;

    public SensorService(
            SensorRepository sensorRepository,
            SensorReadingRepository readingRepository) {

        this.sensorRepository = sensorRepository;
        this.readingRepository = readingRepository;
    }

    public Sensor createSensor(Sensor sensor) {

        if (sensor.getStatus() == null) {
            sensor.setStatus("ACTIVE");
        }

        return sensorRepository.save(sensor);
    }

    public List<Sensor> getAllSensors() {
        return sensorRepository.findAll();
    }

    public Sensor getSensorById(Long id) {
        return sensorRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Sensor not found with id: " + id));
    }

    public Sensor updateSensor(Long id, Sensor updatedSensor) {

        Sensor existing = getSensorById(id);

        existing.setFarmId(updatedSensor.getFarmId());
        existing.setSensorType(updatedSensor.getSensorType());
        existing.setSensorName(updatedSensor.getSensorName());
        existing.setLocation(updatedSensor.getLocation());
        existing.setStatus(updatedSensor.getStatus());
        existing.setInstalledDate(updatedSensor.getInstalledDate());

        return sensorRepository.save(existing);
    }

    public void deleteSensor(Long id) {

        Sensor sensor = getSensorById(id);

        sensorRepository.delete(sensor);
    }

    public SensorReading addReading(SensorReading reading) {

        Sensor sensor = getSensorById(reading.getSensorId());

        reading.setFarmId(sensor.getFarmId());

        sensor.setLastReadingTime(LocalDateTime.now());

        sensorRepository.save(sensor);

        return readingRepository.save(reading);
    }

    public List<SensorReading> getSensorReadings(Long sensorId) {

        getSensorById(sensorId);

        return readingRepository.findBySensorId(sensorId);
    }

    public List<SensorReading> getFarmReadings(Long farmId) {

        return readingRepository.findByFarmId(farmId);
    }

    public List<SensorReading> getLatestFarmReadings(Long farmId) {

        return readingRepository
                .findTop10ByFarmIdOrderByTimestampDesc(farmId);
    }
}