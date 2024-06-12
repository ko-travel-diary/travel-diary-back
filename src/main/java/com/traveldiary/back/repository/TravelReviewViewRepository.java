package com.traveldiary.back.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.traveldiary.back.entity.TravelReviewViewEntity;

@Repository
public interface TravelReviewViewRepository extends JpaRepository<TravelReviewViewEntity, Integer>{



}
