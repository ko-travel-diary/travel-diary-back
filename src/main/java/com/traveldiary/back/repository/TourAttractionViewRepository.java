package com.traveldiary.back.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.traveldiary.back.entity.TourAttractionViewEntity;

@Repository
public interface TourAttractionViewRepository extends JpaRepository<TourAttractionViewEntity, Integer>{

    List<TourAttractionViewEntity> findBy();
    List<TourAttractionViewEntity> findByTourAttractionsNameContains(String tourAttractionsName);
    @Query(
        value=
            "SELECT t " +
            "FROM TourAttractionViewEntity as t " +
            "WHERE t.tourAttractionsLat >= (:lat - 0.03) " +
                "AND t.tourAttractionsLat <= (:lat + 0.03) " +
                "AND t.tourAttractionsLng >= (:lng - 0.07) " +
                "AND t.tourAttractionsLng <= (:lng + 0.07) "
    )
    List<TourAttractionViewEntity> getTourAttractionsRangeList(
        @Param("lat") Double lat,
        @Param("lng") Double lng
    );

}
