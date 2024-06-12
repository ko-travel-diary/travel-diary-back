package com.traveldiary.back.entity;

import com.traveldiary.back.common.object.ExpenditureListItem;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "travelScheduleExpenditure")
@Table(name = "travel_schedule_expenditure")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TravelScheduleExpenditureEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer travelScheduleExpenditureNumber;

    private String travelScheduleExpenditureDetail;
    private Integer travelScheduleExpenditure;
    private Integer travelScheduleNumber;

    public TravelScheduleExpenditureEntity(ExpenditureListItem item, Integer travelScheduleNumber) {
        this.travelScheduleExpenditureDetail = item.getTravelScheduleExpenditureDetail();
        this.travelScheduleExpenditure = item.getTravelScheduleExpenditure();
        this.travelScheduleNumber = travelScheduleNumber;
    }

}