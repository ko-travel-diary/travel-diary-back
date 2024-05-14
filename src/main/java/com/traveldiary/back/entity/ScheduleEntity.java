package com.traveldiary.back.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "schedule")
@Table(name = "schedule")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleEntity {
    private Integer scheduleNumber;
    private String scheduleDate;
    private String scheduleContent;
    private String scheduleStartTime;
    private String scheduleEndTime;
    private Integer travelScheduleNumber;
}
