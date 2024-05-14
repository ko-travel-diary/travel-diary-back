package com.traveldiary.back.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "travel_schedule")
@Table(name = "travel_schedule")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TravelScheduleEntity {
    private Integer travelScheduleNumber;
    private String travelScheduleName;
    private Integer travelSchedulePeople;
    private Integer travelScheduleTotalMoney;
    private String travelScheduleWriterId;
}
