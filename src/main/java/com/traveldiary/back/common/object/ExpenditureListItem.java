package com.traveldiary.back.common.object;

import java.util.ArrayList;
import java.util.List;

import com.traveldiary.back.entity.TravelScheduleExpenditureEntity;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ExpenditureListItem {

    private String travelScheduleExpenditureDetail;
    private Integer travelScheduleExpenditure;	

    private ExpenditureListItem(TravelScheduleExpenditureEntity travelScheduleExpenditureEntity) {
        this.travelScheduleExpenditureDetail = travelScheduleExpenditureEntity.getTravelScheduleExpenditureDetail();
        this.travelScheduleExpenditure = travelScheduleExpenditureEntity.getTravelScheduleExpenditure();
    }

    public static List<ExpenditureListItem> getExpenditure(List<TravelScheduleExpenditureEntity> entities) {

        List<ExpenditureListItem> expenditureList = new ArrayList<>();

        for(TravelScheduleExpenditureEntity entity: entities) {
            ExpenditureListItem expenditureListItem = new ExpenditureListItem(entity);
            expenditureList.add(expenditureListItem);
        }

        return expenditureList;

    }
    
}
