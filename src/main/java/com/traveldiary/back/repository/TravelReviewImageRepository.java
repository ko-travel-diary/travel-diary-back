package com.traveldiary.back.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.traveldiary.back.entity.TravelReviewImageEntity;

@Repository
public interface TravelReviewImageRepository extends JpaRepository<TravelReviewImageEntity, Integer>{
    
    TravelReviewImageEntity findByTravelReviewNumber(Integer travelReviewNumber);
    void deleteByTravelReviewNumber(Integer reviewNumber);
}
