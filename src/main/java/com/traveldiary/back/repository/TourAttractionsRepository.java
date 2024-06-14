package com.traveldiary.back.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.traveldiary.back.entity.TourAttractionsEntity;

import jakarta.transaction.Transactional;

@Repository
public interface TourAttractionsRepository extends JpaRepository<TourAttractionsEntity, Integer> {

    boolean existsByTourAttractionsName(String tourAttractionsName);

    TourAttractionsEntity findByTourAttractionsNumber(Integer tourAttractionsNumber);

    @Transactional
    void deleteByTourAttractionsNumber(Integer tourAttractionsNumber);

}