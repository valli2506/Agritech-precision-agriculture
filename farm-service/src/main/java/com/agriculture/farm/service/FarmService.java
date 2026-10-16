package com.agriculture.farm.service;

import com.agriculture.farm.entity.Farm;
import com.agriculture.farm.repository.FarmRepository;
import org.springframework.stereotype.Service;
import com.agriculture.farm.exception.FarmNotFoundException;

import java.util.List;

@Service
public class FarmService {

    private final FarmRepository farmRepository;

    public FarmService(FarmRepository farmRepository) {
        this.farmRepository = farmRepository;
    }

    public Farm createFarm(Farm farm) {
        return farmRepository.save(farm);
    }

    public List<Farm> getAllFarms() {
        return farmRepository.findAll();
    }

    public Farm getFarmById(Long id) {
        return farmRepository.findById(id)
        		.orElseThrow(() ->
                new FarmNotFoundException("Farm not found with id: " + id));
    }

    public List<Farm> getFarmsByFarmerId(Long farmerId) {
        return farmRepository.findByFarmerId(farmerId);
    }

    public Farm updateFarm(Long id, Farm updatedFarm) {

        Farm existingFarm = getFarmById(id);

        existingFarm.setFarmerId(updatedFarm.getFarmerId());
        existingFarm.setFarmName(updatedFarm.getFarmName());
        existingFarm.setLocation(updatedFarm.getLocation());
        existingFarm.setArea(updatedFarm.getArea());
        existingFarm.setCropType(updatedFarm.getCropType());
        existingFarm.setPlantingDate(updatedFarm.getPlantingDate());
        existingFarm.setStatus(updatedFarm.getStatus());

        return farmRepository.save(existingFarm);
    }

    public void deleteFarm(Long id) {
        Farm farm = getFarmById(id);
        farmRepository.delete(farm);
    }
}