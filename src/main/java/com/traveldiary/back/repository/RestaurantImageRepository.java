package com.traveldiary.back.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.traveldiary.back.entity.RestaurantImageEntity;

@Repository
public interface RestaurantImageRepository extends JpaRepository<RestaurantImageEntity, Integer> {

    List<RestaurantImageEntity> findByRestaurantNumber(Integer RestaurantNumber);

}
