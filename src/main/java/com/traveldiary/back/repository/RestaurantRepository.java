package com.traveldiary.back.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.traveldiary.back.entity.RestaurantEntity;
import com.traveldiary.back.repository.resultSet.GetRestaurantResultSet;

@Repository
public interface RestaurantRepository extends JpaRepository<RestaurantEntity, Integer>{
    @Query(
        value=
        "SELECT image, " + 
                "r.restaurant_number as restaurantNumber, " + 
                "r.restaurant_name as restaurantName, " + 
                "r.restaurant_location as restaurantLocation, " + 
                "r.restaurant_tel_number as restaurantTelNumber, " +                 
                "r.restaurant_outline as restaurantOutline, " + 
                "r.restaurant_main_menu as restaurantMainMenu, " + 
                "r.restaurant_service_menu as restaurantServiceMenu, " + 
                "r.restaurant_lat as restaurantLat, " + 
                "r.restaurant_lng as restaurantLng, " + 
                "r.restaurant_hours as restaurantHours FROM restaurant r LEFT JOIN ( " +
            "SELECT restaurant_number, ANY_VALUE(restaurant_image_url) as image " +
            "FROM restaurant_image " +
            "GROUP BY restaurant_number " +
        ") i " +
        "ON r.restaurant_number = i.restaurant_number ",
        nativeQuery=true
    )
    List<GetRestaurantResultSet> getRestaurantList ();

    @Query(
        value=
        "SELECT image, " + 
                "r.restaurant_number as restaurantNumber, " + 
                "r.restaurant_name as restaurantName, " + 
                "r.restaurant_location as restaurantLocation, " + 
                "r.restaurant_tel_number as restaurantTelNumber, " +                 
                "r.restaurant_outline as restaurantOutline, " + 
                "r.restaurant_main_menu as restaurantMainMenu, " + 
                "r.restaurant_service_menu as restaurantServiceMenu, " + 
                "r.restaurant_lat as restaurantLat, " + 
                "r.restaurant_lng as restaurantLng, " + 
                "r.restaurant_recommend_count as restaurantRecommendCount, " + 
                "r.restaurant_hours as restaurantHours FROM restaurant r LEFT JOIN ( " +
            "SELECT restaurant_number, ANY_VALUE(restaurant_image_url) as image " +
            "FROM restaurant_image " +
            "GROUP BY restaurant_number " +
        ") i " +
        "ON r.restaurant_number = i.restaurant_number " +
        "WHERE r.restaurant_lat >= (:lat - 0.025) " +
        "AND r.restaurant_lat <= (:lat + 0.025) " +
        "AND r.restaurant_lng >= (:lng - 0.05) " +
        "AND r.restaurant_lng <= (:lng + 0.05) ",
        nativeQuery=true
    )
    List<GetRestaurantResultSet> getRestaurantRangeList (
        @Param("lat") Double lat,
        @Param("lng") Double lng
    );

    @Query(
        value=
        "SELECT image, " + 
                "r.restaurant_number as restaurantNumber, " + 
                "r.restaurant_name as restaurantName, " + 
                "r.restaurant_location as restaurantLocation, " + 
                "r.restaurant_tel_number as restaurantTelNumber, " +                 
                "r.restaurant_outline as restaurantOutline, " + 
                "r.restaurant_main_menu as restaurantMainMenu, " + 
                "r.restaurant_service_menu as restaurantServiceMenu, " + 
                "r.restaurant_hours as restaurantHours FROM restaurant r LEFT JOIN ( " +
            "SELECT restaurant_number, ANY_VALUE(restaurant_image_url) as image " +
            "FROM restaurant_image " +
            "GROUP BY restaurant_number " +
        ") i " +
        "ON r.restaurant_number = i.restaurant_number " +
        "WHERE r.restaurant_name LIKE %:searchWord%",
        nativeQuery=true
    )
    List<GetRestaurantResultSet> getSearchRestaurantList (String searchWord);

    RestaurantEntity findByRestaurantNumber (Integer RestaurantNumber);
    boolean existsByRestaurantName (String restaurantName);
}
