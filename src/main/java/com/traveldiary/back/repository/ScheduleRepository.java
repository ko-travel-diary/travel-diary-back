package com.traveldiary.back.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.traveldiary.back.entity.ScheduleEntity;

@Repository
public interface ScheduleRepository extends JpaRepository<ScheduleEntity, Integer>{

    ScheduleEntity findByTravelScheduleNumber(Integer travelScheduleNumber);

}
