package com.traveldiary.back.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.traveldiary.back.entity.TravelReviewImageEntity;

import jakarta.transaction.Transactional;

@Repository
public interface TravelReviewImageRepository extends JpaRepository<TravelReviewImageEntity, Integer> {

    List<TravelReviewImageEntity> findByTravelReviewNumber(Integer travelReviewNumber);

    @Transactional
    void deleteByTravelReviewNumberIn(List<Integer> travelReviewNumber);

    @Transactional
    void deleteByTravelReviewNumber(Integer travelReviewNumber);

}
