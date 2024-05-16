package com.traveldiary.back.service;

import org.springframework.http.ResponseEntity;

import com.traveldiary.back.dto.request.schedule.PatchScheduleRequestDto;
import com.traveldiary.back.dto.request.schedule.PostScheduleRequestDto;
import com.traveldiary.back.dto.response.ResponseDto;

public interface ScheduleService {
    
    ResponseEntity<ResponseDto> postSchedule(PostScheduleRequestDto dto, String userId);
    ResponseEntity<ResponseDto> patchSchedule(PatchScheduleRequestDto dto, String userId, Integer number);

}
