package com.traveldiary.back.dto.response.schedule;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.traveldiary.back.common.object.ExpenditureListItem;
import com.traveldiary.back.common.object.ScheduleListItem;
import com.traveldiary.back.dto.response.ResponseCode;
import com.traveldiary.back.dto.response.ResponseDto;
import com.traveldiary.back.dto.response.ResponseMessage;
import com.traveldiary.back.entity.ScheduleEntity;
import com.traveldiary.back.entity.TravelScheduleEntity;
import com.traveldiary.back.entity.TravelScheduleExpenditureEntity;

import lombok.Getter;

@Getter
public class GetScheduleDetailResponseDto extends ResponseDto {
    
    private String travelScheduleName;
    private Integer travelSchedulePeople;
    private Integer travelScheduleTotalMoney;
    private List<ExpenditureListItem> expenditureList;
    private List<ScheduleListItem> scheduleList;

    private GetScheduleDetailResponseDto(TravelScheduleEntity entity, List<TravelScheduleExpenditureEntity> expendEntities, List<ScheduleEntity> scheduleEntities) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.travelScheduleName = entity.getTravelScheduleName();
        this.travelSchedulePeople = entity.getTravelSchedulePeople();
        this.travelScheduleTotalMoney = entity.getTravelScheduleTotalMoney();
        this.expenditureList = ExpenditureListItem.getExpenditure(expendEntities);
        this.scheduleList = ScheduleListItem.getSchedule(scheduleEntities);
    }

    public static ResponseEntity<GetScheduleDetailResponseDto> success(TravelScheduleEntity entity, List<TravelScheduleExpenditureEntity> expendEntities, List<ScheduleEntity> scheduleEntities) {
        GetScheduleDetailResponseDto response = new GetScheduleDetailResponseDto(entity, expendEntities, scheduleEntities);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}