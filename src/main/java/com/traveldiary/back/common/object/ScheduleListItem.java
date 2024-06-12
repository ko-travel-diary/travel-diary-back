package com.traveldiary.back.common.object;

import java.util.ArrayList;
import java.util.List;

import com.traveldiary.back.entity.ScheduleEntity;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ScheduleListItem {
    
    private String scheduleDate;
    private String scheduleContent;
    private String scheduleStartTime;
    private String scheduleEndTime;

    private ScheduleListItem(ScheduleEntity scheduleEntity) {
        this.scheduleDate = scheduleEntity.getScheduleDate();
        this.scheduleContent = scheduleEntity.getScheduleContent();
        this.scheduleStartTime = scheduleEntity.getScheduleStartTime();
        this.scheduleEndTime = scheduleEntity.getScheduleEndTime();
    }

    public static List<ScheduleListItem> getSchedule(List<ScheduleEntity> entities) {

        List<ScheduleListItem> scheduleList = new ArrayList<>();

        for (ScheduleEntity entity: entities) {
            ScheduleListItem scheduleListItem = new ScheduleListItem(entity);
            scheduleList.add(scheduleListItem);
        }

        return scheduleList;

    }

}
