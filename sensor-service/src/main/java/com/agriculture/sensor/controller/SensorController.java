package com.agriculture.sensor.controller;

import com.agriculture.sensor.entity.Sensor;
import com.agriculture.sensor.entity.SensorReading;
import com.agriculture.sensor.service.SensorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sensors")
public class SensorController {

    private final SensorService sensorService;

    public SensorController(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    @PostMapping
    public ResponseEntity<Sensor> createSensor(
            @RequestBody Sensor sensor) {

        return new ResponseEntity<>(
                sensorService.createSensor(sensor),
                HttpStatus.CREATED
        );
    }

    @GetMapping
    public ResponseEntity<List<Sensor>> getAllSensors() {

        return ResponseEntity.ok(
                sensorService.getAllSensors()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Sensor> getSensorById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                sensorService.getSensorById(id)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<Sensor> updateSensor(
            @PathVariable Long id,
            @RequestBody Sensor sensor) {

        return ResponseEntity.ok(
                sensorService.updateSensor(id, sensor)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSensor(
            @PathVariable Long id) {

        sensorService.deleteSensor(id);

        return ResponseEntity.ok(
                "Sensor deleted successfully"
        );
    }

    @PostMapping("/readings")
    public ResponseEntity<SensorReading> addReading(
            @RequestBody SensorReading reading) {

        return new ResponseEntity<>(
                sensorService.addReading(reading),
                HttpStatus.CREATED
        );
    }

    @GetMapping("/{sensorId}/readings")
    public ResponseEntity<List<SensorReading>> getSensorReadings(
            @PathVariable Long sensorId) {

        return ResponseEntity.ok(
                sensorService.getSensorReadings(sensorId)
        );
    }

    @GetMapping("/farm/{farmId}/readings")
    public ResponseEntity<List<SensorReading>> getFarmReadings(
            @PathVariable Long farmId) {

        return ResponseEntity.ok(
                sensorService.getFarmReadings(farmId)
        );
    }

    @GetMapping("/farm/{farmId}/latest")
    public ResponseEntity<List<SensorReading>> getLatestFarmReadings(
            @PathVariable Long farmId) {

        return ResponseEntity.ok(
                sensorService.getLatestFarmReadings(farmId)
        );
    }
}