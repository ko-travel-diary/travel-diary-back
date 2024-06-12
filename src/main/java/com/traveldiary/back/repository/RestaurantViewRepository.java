package com.traveldiary.back.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.traveldiary.back.entity.RestaurantViewEntity;
import com.traveldiary.back.repository.resultSet.GetRestaurantResultSet;

@Repository
public interface RestaurantViewRepository extends JpaRepository<RestaurantViewEntity, Integer>{

    @Query(
        value=
        "SELECT * " +
        "FROM travel_diary.restaurant_view ",
        nativeQuery=true
    )
    List<GetRestaurantResultSet> getRestaurantList();

    @Query(
        value=
        "SELECT * " +
        "FROM travel_diary.restaurant_view " +
        "WHERE r.restaurant_name LIKE %:searchWord% ",
        nativeQuery=true
    )
    List<GetRestaurantResultSet> getSearchRestaurantList(String searchWord);

    @Query(
        value=
        "SELECT * " +
        "FROM travel_diary.restaurant_view " +
        "WHERE r.restaurant_lat >= (:lat - 0.03) " +
            "AND r.restaurant_lat <= (:lat + 0.03) " +
            "AND r.restaurant_lng >= (:lng - 0.07) " +
            "AND r.restaurant_lng <= (:lng + 0.07) ",
        nativeQuery=true
    )
    List<GetRestaurantResultSet> getRestaurantRangeList(
        @Param("lat") Double lat,
        @Param("lng") Double lng
    );

}
