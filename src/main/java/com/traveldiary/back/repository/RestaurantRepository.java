package com.traveldiary.back.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.traveldiary.back.entity.RestaurantEntity;
import com.traveldiary.back.repository.resultSet.GetRestaurantResultSet;

@Repository
public interface RestaurantRepository extends JpaRepository<RestaurantEntity, Integer>{
    @Query(
        value=
        "SELECT image, " + 
                "r.restaurant_name as restaurantName, " + 
                "r.restaurant_location as restaurantLocation, " + 
                "r.restaurant_tel_number as restaurantTelNumber, " + 
                "r.restaurant_hours as restaurantHours FROM restaurant r LEFT JOIN ( " +
            "SELECT restaurant_number, ANY_VALUE(restaurant_image_url) as image " +
            "FROM restaurant_image " +
            "GROUP BY restaurant_number " +
        ") i " +
        "ON r.restaurant_number = i.restaurant_number ",
        nativeQuery=true
    )
    List<GetRestaurantResultSet> getRestaurantList ();

    RestaurantEntity findByRestaurantNumber (Integer RestaurantNumber);
    boolean existsByRestaurantName (String restaurantName);
}
