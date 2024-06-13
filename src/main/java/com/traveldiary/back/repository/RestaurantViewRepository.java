package com.traveldiary.back.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.traveldiary.back.entity.RestaurantViewEntity;

@Repository
public interface RestaurantViewRepository extends JpaRepository<RestaurantViewEntity, Integer> {

    List<RestaurantViewEntity> findBy();
    List<RestaurantViewEntity> findByRestaurantNameContains(String restaurantName);

    @Query(
        value=
            "SELECT r " +
            "FROM RestaurantViewEntity as r " +
            "WHERE r.restaurantLat >= (:lat - 0.03) " +
                "AND r.restaurantLat <= (:lat + 0.03) " +
                "AND r.restaurantLng >= (:lng - 0.07) " +
                "AND r.restaurantLng <= (:lng + 0.07) "
    )
    List<RestaurantViewEntity> getRestaurantRangeList(
        @Param("lat") Double lat,
        @Param("lng") Double lng
    );

}
