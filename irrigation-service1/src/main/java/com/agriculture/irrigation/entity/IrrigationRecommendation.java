package com.agriculture.irrigation.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "irrigation_recommendations")
public class IrrigationRecommendation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long farmId;

    private Long cropId;

    private Double soilMoisture;

    private Double rainfall;

    private Double recommendedAmount;

    private String recommendation;

    private String irrigationStatus;

    private LocalDateTime scheduledTime;

    private LocalDateTime createdAt;

    public IrrigationRecommendation() {
    }

    @PrePersist
    public void onCreate() {
        createdAt = LocalDateTime.now();
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

    public Long getCropId() {
        return cropId;
    }

    public void setCropId(Long cropId) {
        this.cropId = cropId;
    }

    public Double getSoilMoisture() {
        return soilMoisture;
    }

    public void setSoilMoisture(Double soilMoisture) {
        this.soilMoisture = soilMoisture;
    }

    public Double getRainfall() {
        return rainfall;
    }

    public void setRainfall(Double rainfall) {
        this.rainfall = rainfall;
    }

    public Double getRecommendedAmount() {
        return recommendedAmount;
    }

    public void setRecommendedAmount(Double recommendedAmount) {
        this.recommendedAmount = recommendedAmount;
    }

    public String getRecommendation() {
        return recommendation;
    }

    public void setRecommendation(String recommendation) {
        this.recommendation = recommendation;
    }

    public String getIrrigationStatus() {
        return irrigationStatus;
    }

    public void setIrrigationStatus(String irrigationStatus) {
        this.irrigationStatus = irrigationStatus;
    }

    public LocalDateTime getScheduledTime() {
        return scheduledTime;
    }

    public void setScheduledTime(LocalDateTime scheduledTime) {
        this.scheduledTime = scheduledTime;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}