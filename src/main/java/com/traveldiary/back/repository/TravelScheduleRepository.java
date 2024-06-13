package com.traveldiary.back.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.traveldiary.back.entity.TravelScheduleEntity;

import jakarta.transaction.Transactional;

@Repository
public interface TravelScheduleRepository extends JpaRepository<TravelScheduleEntity, Integer> {

    TravelScheduleEntity findByTravelScheduleNumber(Integer travelScheduleNumber);
    List<TravelScheduleEntity> findByTravelScheduleWriterId(String travelScheduleWriterId);

    @Transactional
    void deleteByTravelScheduleNumber(Integer travelScheduleNumber);

}
