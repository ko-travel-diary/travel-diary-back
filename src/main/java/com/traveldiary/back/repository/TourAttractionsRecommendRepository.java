package com.traveldiary.back.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.traveldiary.back.entity.TourAttractionsRecommendEntity;
import com.traveldiary.back.entity.pk.TourRecommendPk;

@Repository
public interface TourAttractionsRecommendRepository extends JpaRepository<TourAttractionsRecommendEntity, TourRecommendPk> {

    List<TourAttractionsRecommendEntity> findByUserId(String userId);
    boolean existsByUserIdAndTourAttractionsNumber(String userId, Integer tourAttractionsNumber);

}
