package com.traveldiary.back.service;

import org.springframework.http.ResponseEntity;

import com.traveldiary.back.dto.request.schedule.PatchScheduleRequestDto;
import com.traveldiary.back.dto.request.schedule.PostScheduleRequestDto;
import com.traveldiary.back.dto.response.ResponseDto;
import com.traveldiary.back.dto.response.schedule.GetScheduleDetailResponseDto;
import com.traveldiary.back.dto.response.schedule.GetScheduleListResponseDto;

public interface ScheduleService {
    
    ResponseEntity<ResponseDto> postSchedule(PostScheduleRequestDto dto, String userId);

    ResponseEntity<? super GetScheduleListResponseDto> getScheduleList(String userId);
    ResponseEntity<? super GetScheduleDetailResponseDto> getScheduleDetail(String userId, Integer travelScheduleNumber);

    ResponseEntity<ResponseDto> patchSchedule(PatchScheduleRequestDto dto, String userId, Integer travelScheduleNumber);
    
    ResponseEntity<ResponseDto> deleteSchedule(String userId, Integer travelScheduleWriterId);

}
