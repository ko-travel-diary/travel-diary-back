package com.traveldiary.back.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.traveldiary.back.entity.RestaurantEntity;

@Repository
public interface RestaurantRepository extends JpaRepository<RestaurantEntity, Integer> {

    RestaurantEntity findByRestaurantNumber(Integer RestaurantNumber);
    boolean existsByRestaurantName(String restaurantName);

}
