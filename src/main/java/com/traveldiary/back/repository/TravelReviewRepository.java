package com.traveldiary.back.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.traveldiary.back.entity.TravelReviewEntity;

@Repository
public interface TravelReviewRepository extends JpaRepository<TravelReviewEntity, Integer> {

    boolean existsByReviewNumber(Integer reviewNumber);
    boolean existsByTravelScheduleNumber(Integer travelScheduleNumber);

    TravelReviewEntity findByReviewNumber(Integer reviewNumber);
    TravelReviewEntity findByTravelScheduleNumber(Integer travelScheduleNumber);
    
    List<TravelReviewEntity> findByOrderByReviewNumberDesc();
    List<TravelReviewEntity> findByReviewWriterId(String userId);

}
