package com.traveldiary.back.common.object;

import java.util.List;
import java.util.ArrayList;

import com.traveldiary.back.entity.TravelScheduleEntity;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ScheduleListViewItem {
    
    private String travelScheduleName;
    private Integer travelScheduleNumber;

    private ScheduleListViewItem(TravelScheduleEntity travelScheduleEntity) {

        this.travelScheduleName = travelScheduleEntity.getTravelScheduleName();
        this.travelScheduleNumber = travelScheduleEntity.getTravelScheduleNumber();

    }

    public static List<ScheduleListViewItem> getScheduleViewList(List<TravelScheduleEntity> entities) {
        
        List<ScheduleListViewItem> viewList = new ArrayList<>();

        for (TravelScheduleEntity entity: entities) {
            
            ScheduleListViewItem scheduleListViewItem = new ScheduleListViewItem(entity);
            viewList.add(scheduleListViewItem);

        }

        return viewList;

    }

}
