package com.traveldiary.back.dto.response.schedule;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.traveldiary.back.dto.response.ResponseCode;
import com.traveldiary.back.dto.response.ResponseDto;
import com.traveldiary.back.dto.response.ResponseMessage;

import lombok.Getter;

@Getter
public class GetScheduleListResponseDto extends ResponseDto {

    private List<String> travelScheduleName;
    private Integer travelScheduleNumber;

    private GetScheduleListResponseDto(List<String> travelScheduleName, Integer travelScheduleNumber) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.travelScheduleName = travelScheduleName;
        this.travelScheduleNumber = travelScheduleNumber;
    }

    public static ResponseEntity<GetScheduleListResponseDto> success (List<String> travelScheduleName, Integer travelScheduleNumber) {
        GetScheduleListResponseDto reponseBody = new GetScheduleListResponseDto(travelScheduleName, travelScheduleNumber);
        return ResponseEntity.status(HttpStatus.OK).body(reponseBody);
    }
    
}
