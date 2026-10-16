package com.agriculture.crop.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "crops")
public class Crop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long farmId;

    @Column(nullable = false)
    private String cropName;

    private String cropType;

    private String growthStage;

    private LocalDate plantingDate;

    private LocalDate expectedHarvestDate;

    private Double requiredSoilMoisture;

    private Double optimalTemperature;

    private String status;

    public Crop() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFarmId() {
        return farmId;
    }

    public void setFarmId(Long farmId) {
        this.farmId = farmId;
    }

    public String getCropName() {
        return cropName;
    }

    public void setCropName(String cropName) {
        this.cropName = cropName;
    }

    public String getCropType() {
        return cropType;
    }

    public void setCropType(String cropType) {
        this.cropType = cropType;
    }

    public String getGrowthStage() {
        return growthStage;
    }

    public void setGrowthStage(String growthStage) {
        this.growthStage = growthStage;
    }

    public LocalDate getPlantingDate() {
        return plantingDate;
    }

    public void setPlantingDate(LocalDate plantingDate) {
        this.plantingDate = plantingDate;
    }

    public LocalDate getExpectedHarvestDate() {
        return expectedHarvestDate;
    }

    public void setExpectedHarvestDate(LocalDate expectedHarvestDate) {
        this.expectedHarvestDate = expectedHarvestDate;
    }

    public Double getRequiredSoilMoisture() {
        return requiredSoilMoisture;
    }

    public void setRequiredSoilMoisture(Double requiredSoilMoisture) {
        this.requiredSoilMoisture = requiredSoilMoisture;
    }

    public Double getOptimalTemperature() {
        return optimalTemperature;
    }

    public void setOptimalTemperature(Double optimalTemperature) {
        this.optimalTemperature = optimalTemperature;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}