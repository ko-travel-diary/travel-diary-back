package com.traveldiary.back.entity;

import java.util.ArrayList;
import java.util.List;

import com.traveldiary.back.common.object.ScheduleListItem;
import com.traveldiary.back.dto.request.schedule.PatchScheduleRequestDto;
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

@Entity(name = "schedule")
@Table(name = "schedule")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer scheduleNumber;
    private String scheduleDate;
    private String scheduleContent;
    private String scheduleStartTime;
    private String scheduleEndTime;
    private Integer travelScheduleNumber;

    public ScheduleEntity(ScheduleListItem item, Integer travelScheduleNumber) {
        this.scheduleDate = item.getScheduleDate();
        this.scheduleContent = item.getScheduleContent();
        this.scheduleStartTime = item.getScheduleStartTime();
        this.scheduleEndTime = item.getScheduleEndTime();
        this.travelScheduleNumber = travelScheduleNumber;
    }
}
