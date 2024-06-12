package com.traveldiary.back.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.traveldiary.back.entity.TourAttractionsImageEntity;

@Repository
public interface TourAttractionsImageRepository extends JpaRepository<TourAttractionsImageEntity, Integer>{

    List<TourAttractionsImageEntity> findByTourAttractionsNumber(Integer TourAttractionsNumber);

}
