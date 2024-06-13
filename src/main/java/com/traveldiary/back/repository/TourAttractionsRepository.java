package com.traveldiary.back.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.traveldiary.back.entity.TourAttractionsEntity;

@Repository
public interface TourAttractionsRepository extends JpaRepository<TourAttractionsEntity, Integer> {

    TourAttractionsEntity findByTourAttractionsNumber(Integer tourAttractionsNumber);
    boolean existsByTourAttractionsName(String tourAttractionsName);

}