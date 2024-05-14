package com.traveldiary.back.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.traveldiary.back.entity.TravelReviewEntity;

@Repository
public interface TravelReviewRepository extends JpaRepository<TravelReviewEntity, Integer>{

    TravelReviewEntity findByReviewNumber(Integer reviewNumber);
}