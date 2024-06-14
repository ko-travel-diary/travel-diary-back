package com.traveldiary.back.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.traveldiary.back.entity.RestaurantRecommendEntity;
import com.traveldiary.back.entity.pk.RestRecommendPk;

import jakarta.transaction.Transactional;

@Repository
public interface RestaurantRecommendRepository extends JpaRepository<RestaurantRecommendEntity, RestRecommendPk> {

    boolean existsByUserIdAndRestaurantNumber(String userId, Integer restaurantNumber);

    List<RestaurantRecommendEntity> findByUserId(String userId);

    @Transactional
    void deleteByUserId(String userId);

    @Transactional
    void deleteByRestaurantNumber(Integer restaurantNumber);

}
