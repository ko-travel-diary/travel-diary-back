package com.traveldiary.back.dto.response.schedule;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.traveldiary.back.common.object.ScheduleListViewItem;
import com.traveldiary.back.dto.response.ResponseCode;
import com.traveldiary.back.dto.response.ResponseDto;
import com.traveldiary.back.dto.response.ResponseMessage;
import com.traveldiary.back.entity.TravelScheduleEntity;

import lombok.Getter;

@Getter
public class GetScheduleListResponseDto extends ResponseDto {

    private List<ScheduleListViewItem> scheduleListViewItems;

    private GetScheduleListResponseDto(List<TravelScheduleEntity> travelScheduleEntities) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.scheduleListViewItems = ScheduleListViewItem.getScheduleViewList(travelScheduleEntities);
    }

    public static ResponseEntity<GetScheduleListResponseDto> success(List<TravelScheduleEntity> travelScheduleEntities) {
        GetScheduleListResponseDto reponseBody = new GetScheduleListResponseDto(travelScheduleEntities);
        return ResponseEntity.status(HttpStatus.OK).body(reponseBody);
    }
    
}