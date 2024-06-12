package com.traveldiary.back.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.traveldiary.back.entity.TravelFavoriteEntity;
import com.traveldiary.back.entity.pk.FavoritePk;

@Repository
public interface TravelFavoriteRepository extends JpaRepository<TravelFavoriteEntity, FavoritePk>{

    List<TravelFavoriteEntity> findByReviewNumber(Integer reviewNumber);
    List<TravelFavoriteEntity> findByUserIdAndReviewNumber(String userId, Integer reviewNumber);
    List<TravelFavoriteEntity> findByUserId(String userId);
    boolean existsByUserIdAndReviewNumber(String userId, Integer reviewNumber);

}
