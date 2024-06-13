package com.traveldiary.back.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.traveldiary.back.entity.ScheduleEntity;

@Repository
public interface ScheduleRepository extends JpaRepository<ScheduleEntity, Integer> {
    
    List<ScheduleEntity> findByTravelScheduleNumber(Integer travelScheduleNumber);
    
    @Transactional
    void deleteByTravelScheduleNumberIn(List<Integer> travelScheduleNumber);

    @Transactional
    void deleteByTravelScheduleNumber(Integer travelScheduleNumber);

}
