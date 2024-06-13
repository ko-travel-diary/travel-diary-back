package com.traveldiary.back.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.traveldiary.back.entity.TravelScheduleExpenditureEntity;

import jakarta.transaction.Transactional;

@Repository
public interface TravelScheduleExpenditureRepository extends JpaRepository<TravelScheduleExpenditureEntity, Integer> {

    List<TravelScheduleExpenditureEntity> findByTravelScheduleNumber(Integer travelScheduleNumber);

    @Transactional
    void deleteByTravelScheduleNumberIn(List<Integer> travelScheduleNumber);

    @Transactional
    void deleteByTravelScheduleNumber(Integer travelScheduleNumber);

}
