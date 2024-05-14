package com.traveldiary.back.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.traveldiary.back.entity.TravelFavoriteEntity;

@Repository
public interface TravelFavoriteRepository extends JpaRepository<TravelFavoriteEntity, String>{
    
    boolean existsByUserIdAndReviewNumber(String userId, Integer reviewNumber);
}
