package com.traveldiary.back.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.traveldiary.back.entity.TourAttractionsRecommendEntity;
import com.traveldiary.back.entity.pk.TourRecommendPk;

import jakarta.transaction.Transactional;

@Repository
public interface TourAttractionsRecommendRepository extends JpaRepository<TourAttractionsRecommendEntity, TourRecommendPk> {

    boolean existsByUserIdAndTourAttractionsNumber(String userId, Integer tourAttractionsNumber);

    List<TourAttractionsRecommendEntity> findByUserId(String userId);

    @Transactional
    void deleteByUserId(String userId);

    @Transactional
    void deleteByTourAttractionsNumber(Integer tourAttractionsNumber);

}
