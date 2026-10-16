package com.agriculture.crop.service;

import com.agriculture.crop.entity.Crop;
import com.agriculture.crop.repository.CropRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CropService {

    private final CropRepository cropRepository;

    public CropService(CropRepository cropRepository) {
        this.cropRepository = cropRepository;
    }

    public Crop createCrop(Crop crop) {

        if (crop.getStatus() == null) {
            crop.setStatus("ACTIVE");
        }

        return cropRepository.save(crop);
    }

    public List<Crop> getAllCrops() {
        return cropRepository.findAll();
    }

    public Crop getCropById(Long id) {

        return cropRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Crop not found with id: " + id
                        ));
    }

    public List<Crop> getCropsByFarm(Long farmId) {

        return cropRepository.findByFarmId(farmId);
    }

    public Crop updateCrop(Long id, Crop updatedCrop) {

        Crop existingCrop = getCropById(id);

        existingCrop.setFarmId(updatedCrop.getFarmId());
        existingCrop.setCropName(updatedCrop.getCropName());
        existingCrop.setCropType(updatedCrop.getCropType());
        existingCrop.setGrowthStage(updatedCrop.getGrowthStage());
        existingCrop.setPlantingDate(updatedCrop.getPlantingDate());
        existingCrop.setExpectedHarvestDate(
                updatedCrop.getExpectedHarvestDate()
        );
        existingCrop.setRequiredSoilMoisture(
                updatedCrop.getRequiredSoilMoisture()
        );
        existingCrop.setOptimalTemperature(
                updatedCrop.getOptimalTemperature()
        );
        existingCrop.setStatus(updatedCrop.getStatus());

        return cropRepository.save(existingCrop);
    }

    public void deleteCrop(Long id) {

        Crop crop = getCropById(id);

        cropRepository.delete(crop);
    }
}