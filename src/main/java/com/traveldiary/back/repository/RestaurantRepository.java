package com.traveldiary.back.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.traveldiary.back.entity.RestaurantEntity;

import jakarta.transaction.Transactional;

@Repository
public interface RestaurantRepository extends JpaRepository<RestaurantEntity, Integer> {

    boolean existsByRestaurantName(String restaurantName);

    RestaurantEntity findByRestaurantNumber(Integer restaurantNumber);

    @Transactional
    void deleteByRestaurantNumber(Integer restaurantNumber);

}
