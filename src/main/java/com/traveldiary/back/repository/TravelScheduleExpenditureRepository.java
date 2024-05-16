package com.traveldiary.back.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.traveldiary.back.entity.TravelScheduleExpenditureEntity;

@Repository
public interface TravelScheduleExpenditureRepository extends JpaRepository<TravelScheduleExpenditureEntity, Integer>{
    
    TravelScheduleExpenditureEntity findByTravelScheduleNumber(Integer travelScheduleNumber);

}
