package com.traveldiary.back.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.traveldiary.back.entity.TravelReviewViewEntity;

@Repository
public interface TravelReviewViewRepository extends JpaRepository<TravelReviewViewEntity, Integer>{

    List<TravelReviewViewEntity> findBy();
    List<TravelReviewViewEntity> findByReviewTitleContainsOrReviewContentContains(String reviewTitle, String reviewContent);
    List<TravelReviewViewEntity> findByReviewWriterIdContains(String reviewWriterId);
    List<TravelReviewViewEntity> findByReviewDatetimeContains(String reviewDatetime);
    List<TravelReviewViewEntity> findByReviewWriterId(String reviewWriterId);
    List<TravelReviewViewEntity> findByReviewTitleContains(String reviewTitle);

}
