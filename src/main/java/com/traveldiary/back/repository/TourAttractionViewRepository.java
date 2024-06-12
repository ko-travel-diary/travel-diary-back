package com.traveldiary.back.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.traveldiary.back.entity.TourAttractionViewEntity;
import com.traveldiary.back.repository.resultSet.GetTourAttractionsResultSet;

@Repository
public interface TourAttractionViewRepository extends JpaRepository<TourAttractionViewEntity, Integer>{

    @Query(
        value=
        "SELECT * " +
        "FROM travel_diary.tour_attraction_view",
        nativeQuery=true
    )
    List<GetTourAttractionsResultSet> getTourAttractionsList();

    
    @Query(
        value=
        "SELECT * " +
        "FROM travel_diary.tour_attraction_view " +
        "WHERE t.tour_attractions_name LIKE %:searchWord%",
        nativeQuery=true
    )
    List<GetTourAttractionsResultSet> getSearchTourAttractionsList(String searchWord);

    @Query(
        value=
        "SELECT * " +
        "FROM travel_diary.tour_attraction_view " +
        "WHERE t.tour_attractions_lat >= (:lat - 0.03) " +
            "AND t.tour_attractions_lat <= (:lat + 0.03) " +
            "AND t.tour_attractions_lng >= (:lng - 0.07) " +
            "AND t.tour_attractions_lng <= (:lng + 0.07) ",
        nativeQuery=true
    )
    List<GetTourAttractionsResultSet> getTourAttractionsRangeList(
        @Param("lat") Double lat,
        @Param("lng") Double lng
    );

}
