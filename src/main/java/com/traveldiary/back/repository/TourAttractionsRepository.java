package com.traveldiary.back.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.traveldiary.back.entity.TourAttractionsEntity;
import com.traveldiary.back.repository.resultSet.GetTourAttractionsResultSet;

@Repository
public interface TourAttractionsRepository extends JpaRepository<TourAttractionsEntity, Integer>{
    @Query(
        value=
        "SELECT image, " + 
                "t.tour_attractions_number as tourAttractionsNumber, " + 
                "t.tour_attractions_name as tourAttractionsName, " + 
                "t.tour_attractions_location as tourAttractionsLocation, " + 
                "t.tour_attractions_tel_number as tourAttractionsTelNumber, " + 
                "t.tour_attractions_outline as tourAttractionsOutline,  " +
                "t.tour_attractions_lat as tourAttractionsLat,  " +
                "t.tour_attractions_lng as tourAttractionsLng,  " +
                "t.tour_attractions_hours as tourAttractionsHours FROM tour_attractions t LEFT JOIN ( " +
            "SELECT tour_attractions_number, ANY_VALUE(tour_attractions_image_url) as image " +
            "FROM tour_attractions_image " +
            "GROUP BY tour_attractions_number " +
        ") i " +
        "ON t.tour_attractions_number = i.tour_attractions_number ",
        nativeQuery=true
    )
    List<GetTourAttractionsResultSet> getTourAttractionsList ();

    @Query(
        value=
        "SELECT image, " + 
                "t.tour_attractions_number as tourAttractionsNumber, " + 
                "t.tour_attractions_name as tourAttractionsName, " + 
                "t.tour_attractions_location as tourAttractionsLocation, " + 
                "t.tour_attractions_tel_number as tourAttractionsTelNumber, " + 
                "t.tour_attractions_outline as tourAttractionsOutline,  " +
                "t.tour_attractions_lat as tourAttractionsLat,  " +
                "t.tour_attractions_lng as tourAttractionsLng,  " +
                "t.tour_attractions_recommend_count as tourAttractionsRecommendCount,  " +
                "t.tour_attractions_hours as tourAttractionsHours FROM tour_attractions t LEFT JOIN ( " +
            "SELECT tour_attractions_number, ANY_VALUE(tour_attractions_image_url) as image " +
            "FROM tour_attractions_image " +
            "GROUP BY tour_attractions_number " +
        ") i " +
        "ON t.tour_attractions_number = i.tour_attractions_number " +
        "WHERE t.tour_attractions_lat >= (:lat - 0.03) " +
        "AND t.tour_attractions_lat <= (:lat + 0.03) " +
        "AND t.tour_attractions_lng >= (:lng - 0.07) " +
        "AND t.tour_attractions_lng <= (:lng + 0.07) ",
        nativeQuery=true
    )
    List<GetTourAttractionsResultSet> getTourAttractionsRangeList (
        @Param("lat") Double lat,
        @Param("lng") Double lng
    );

    @Query(
        value=
        "SELECT image, " + 
                "t.tour_attractions_number as tourAttractionsNumber, " + 
                "t.tour_attractions_name as tourAttractionsName, " + 
                "t.tour_attractions_location as tourAttractionsLocation, " + 
                "t.tour_attractions_tel_number as tourAttractionsTelNumber, " + 
                "t.tour_attractions_outline as tourAttractionsOutline,  " +
                "t.tour_attractions_recommend_count as tourAttractionsRecommendCount, " +
                "t.tour_attractions_lat as tourAttractionsLat,  " +
                "t.tour_attractions_lng as tourAttractionsLng,  " +
                "t.tour_attractions_hours as tourAttractionsHours FROM tour_attractions t LEFT JOIN ( " +
            "SELECT tour_attractions_number, ANY_VALUE(tour_attractions_image_url) as image " +
            "FROM tour_attractions_image " +
            "GROUP BY tour_attractions_number " +
        ") i " +
        "ON t.tour_attractions_number = i.tour_attractions_number "+
        "WHERE t.tour_attractions_name LIKE %:searchWord%",
        nativeQuery=true
    )
    List<GetTourAttractionsResultSet> getSearchTourAttractionsList (String searchWord);

    TourAttractionsEntity findByTourAttractionsNumber (Integer tourAttractionsNumber);

    boolean existsByTourAttractionsName (String tourAttractionsName);
}