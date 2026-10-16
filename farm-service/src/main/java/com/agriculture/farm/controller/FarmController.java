package com.agriculture.farm.controller;

import com.agriculture.farm.entity.Farm;
import com.agriculture.farm.service.FarmService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/farms")
public class FarmController {

    private final FarmService farmService;

    public FarmController(FarmService farmService) {
        this.farmService = farmService;
    }

    @PostMapping
    public ResponseEntity<Farm> createFarm(@RequestBody Farm farm) {

        Farm createdFarm = farmService.createFarm(farm);

        return new ResponseEntity<>(createdFarm, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Farm>> getAllFarms() {

        return ResponseEntity.ok(farmService.getAllFarms());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Farm> getFarmById(@PathVariable Long id) {

        return ResponseEntity.ok(farmService.getFarmById(id));
    }

    @GetMapping("/farmer/{farmerId}")
    public ResponseEntity<List<Farm>> getFarmsByFarmerId(
            @PathVariable Long farmerId) {

        return ResponseEntity.ok(
                farmService.getFarmsByFarmerId(farmerId)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<Farm> updateFarm(
            @PathVariable Long id,
            @RequestBody Farm farm) {

        return ResponseEntity.ok(
                farmService.updateFarm(id, farm)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteFarm(@PathVariable Long id) {

        farmService.deleteFarm(id);

        return ResponseEntity.ok("Farm deleted successfully");
    }
}