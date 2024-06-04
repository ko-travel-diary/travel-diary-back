package com.traveldiary.back.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.traveldiary.back.entity.RestaurantRecommendEntity;
import com.traveldiary.back.entity.pk.RestRecommendPk;

@Repository
public interface RestaurantRecommendRepository extends JpaRepository<RestaurantRecommendEntity, RestRecommendPk>{
    
    List<RestaurantRecommendEntity> findByUserId(String userId);

}
