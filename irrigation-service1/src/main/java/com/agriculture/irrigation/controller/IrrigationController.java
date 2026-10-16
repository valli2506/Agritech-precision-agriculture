package com.agriculture.irrigation.controller;

import com.agriculture.irrigation.entity.IrrigationRecommendation;
import com.agriculture.irrigation.service.IrrigationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/irrigation")
public class IrrigationController {

    private final IrrigationService irrigationService;

    public IrrigationController(
            IrrigationService irrigationService) {

        this.irrigationService = irrigationService;
    }

    @PostMapping("/recommend")
    public ResponseEntity<IrrigationRecommendation>
    generateRecommendation(
            @RequestParam Long farmId) {

        return ResponseEntity.ok(
                irrigationService
                        .generateRecommendation(farmId)
        );
    }

    @GetMapping("/farm/{farmId}")
    public ResponseEntity<List<IrrigationRecommendation>>
    getFarmRecommendations(
            @PathVariable Long farmId) {

        return ResponseEntity.ok(
                irrigationService
                        .getFarmRecommendations(farmId)
        );
    }

    @GetMapping("/farm/{farmId}/latest")
    public ResponseEntity<IrrigationRecommendation>
    getLatestRecommendation(
            @PathVariable Long farmId) {

        return ResponseEntity.ok(
                irrigationService
                        .getLatestRecommendation(farmId)
        );
    }
}