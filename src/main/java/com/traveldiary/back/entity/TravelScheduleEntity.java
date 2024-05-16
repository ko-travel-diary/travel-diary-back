package com.traveldiary.back.entity;

import com.traveldiary.back.dto.request.schedule.PostScheduleRequestDto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "travelSchedule")
@Table(name = "travel_schedule")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TravelScheduleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer travelScheduleNumber;
    private String travelScheduleName;
    private Integer travelSchedulePeople;
    private Integer travelScheduleTotalMoney;
    private String travelScheduleWriterId;

    public TravelScheduleEntity (PostScheduleRequestDto dto, String userId) {
        this.travelScheduleName = dto.getTravelScheduleName();
        this.travelSchedulePeople = dto.getTravelSchedulePeople();
        this.travelScheduleTotalMoney = dto.getTravelScheduleTotalMoney();
        this.travelScheduleWriterId = userId;
    }
}
