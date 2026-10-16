package com.agriculture.irrigation.repository;

import com.agriculture.irrigation.entity.IrrigationRecommendation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IrrigationRepository
        extends JpaRepository<IrrigationRecommendation, Long> {

    List<IrrigationRecommendation>
    findByFarmIdOrderByCreatedAtDesc(Long farmId);

}