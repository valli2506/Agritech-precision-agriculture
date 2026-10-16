package com.agriculture.crop.controller;

import com.agriculture.crop.entity.Crop;
import com.agriculture.crop.service.CropService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/crops")
public class CropController {

    private final CropService cropService;

    public CropController(CropService cropService) {
        this.cropService = cropService;
    }

    @PostMapping
    public ResponseEntity<Crop> createCrop(
            @RequestBody Crop crop) {

        return new ResponseEntity<>(
                cropService.createCrop(crop),
                HttpStatus.CREATED
        );
    }

    @GetMapping
    public ResponseEntity<List<Crop>> getAllCrops() {

        return ResponseEntity.ok(
                cropService.getAllCrops()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Crop> getCropById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                cropService.getCropById(id)
        );
    }

    @GetMapping("/farm/{farmId}")
    public ResponseEntity<List<Crop>> getCropsByFarm(
            @PathVariable Long farmId) {

        return ResponseEntity.ok(
                cropService.getCropsByFarm(farmId)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<Crop> updateCrop(
            @PathVariable Long id,
            @RequestBody Crop crop) {

        return ResponseEntity.ok(
                cropService.updateCrop(id, crop)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCrop(
            @PathVariable Long id) {

        cropService.deleteCrop(id);

        return ResponseEntity.ok(
                "Crop deleted successfully"
        );
    }
}