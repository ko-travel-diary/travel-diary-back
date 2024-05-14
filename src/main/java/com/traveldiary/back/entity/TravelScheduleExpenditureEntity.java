package com.traveldiary.back.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "travel_schedule_expenditure")
@Table(name = "travel_schedule_expenditure")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TravelScheduleExpenditureEntity {
    @Id
    private Integer travelScheduleExpenditureNumber;
    private String travelScheduleExpenditureDetail;
    private Integer travelScheduleExpenditure;
    private Integer travelScheduleNumber;
}
