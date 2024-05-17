package com.traveldiary.back.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.traveldiary.back.dto.request.schedule.PatchScheduleRequestDto;
import com.traveldiary.back.dto.request.schedule.PostScheduleRequestDto;
import com.traveldiary.back.dto.response.ResponseDto;
import com.traveldiary.back.dto.response.schedule.GetScheduleDetailResponseDto;
import com.traveldiary.back.dto.response.schedule.GetScheduleListResponseDto;
import com.traveldiary.back.service.ScheduleService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/traveldiary/v1/schedule")
@RequiredArgsConstructor
public class ScheduleController {
    
    private final ScheduleService scheduleService;

    @PostMapping("/")
    public ResponseEntity<ResponseDto> postSchedule(
        @RequestBody @Valid PostScheduleRequestDto requestBody,
        @AuthenticationPrincipal String userId
    ) {
        ResponseEntity<ResponseDto> response = scheduleService.postSchedule(requestBody, userId);
        return response;
    }

    @PatchMapping("/{travelScheduleNumber}")
    public ResponseEntity<ResponseDto> patchSchedule(
        @RequestBody @Valid PatchScheduleRequestDto requestbody,
        @PathVariable("travelScheduleNumber") Integer travelScheduleNumber,
        @AuthenticationPrincipal String userId
    ) {
        ResponseEntity<ResponseDto> response = scheduleService.patchSchedule(requestbody, userId, travelScheduleNumber);
        return response;
    }

    @DeleteMapping("/{travelScheduleNumber}")
    public ResponseEntity<ResponseDto> deleteSchedule(
        @PathVariable("travelScheduleNumber") Integer travelScheduleNumber,
        @AuthenticationPrincipal String userId
    ) {
        ResponseEntity<ResponseDto> response = scheduleService.deleteSchedule(userId, travelScheduleNumber);
        return response;
    }
    
    @GetMapping("/list")
    public ResponseEntity<? super GetScheduleListResponseDto> getScheduleViewList (
        @AuthenticationPrincipal String userId
    ) {
        ResponseEntity<? super GetScheduleListResponseDto> response = scheduleService.getScheduleList(userId);
        return response;
    }

    @GetMapping("/{travelScheduleNumber}")
    public ResponseEntity<? super GetScheduleDetailResponseDto> getScheduleDetail (
        @PathVariable("travelScheduleNumber") Integer travelScheduleNumber,
        @AuthenticationPrincipal String userId
    ) {
        ResponseEntity<? super GetScheduleDetailResponseDto> response = scheduleService.getScheduleDetail(userId, travelScheduleNumber);
        return response;
    }
}
