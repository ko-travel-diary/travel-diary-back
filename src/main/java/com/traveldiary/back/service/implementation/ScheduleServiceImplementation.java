package com.traveldiary.back.service.implementation;

import java.util.List;
import java.util.ArrayList;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.traveldiary.back.common.object.ExpenditureListItem;
import com.traveldiary.back.common.object.ScheduleListItem;
import com.traveldiary.back.dto.request.schedule.PatchScheduleRequestDto;
import com.traveldiary.back.dto.request.schedule.PostScheduleRequestDto;
import com.traveldiary.back.dto.response.ResponseDto;
import com.traveldiary.back.dto.response.schedule.GetScheduleDetailResponseDto;
import com.traveldiary.back.dto.response.schedule.GetScheduleListResponseDto;
import com.traveldiary.back.entity.ScheduleEntity;
import com.traveldiary.back.entity.TravelScheduleEntity;
import com.traveldiary.back.entity.TravelScheduleExpenditureEntity;
import com.traveldiary.back.repository.ScheduleRepository;
import com.traveldiary.back.repository.TravelScheduleRepository;
import com.traveldiary.back.repository.TravelScheduleExpenditureRepository;
import com.traveldiary.back.repository.UserRepository;
import com.traveldiary.back.service.ScheduleService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ScheduleServiceImplementation implements ScheduleService{

    private final UserRepository userRepository;
    private final ScheduleRepository scheduleRepository;
    private final TravelScheduleRepository travelSchduleRepository;
    private final TravelScheduleExpenditureRepository travelScheduleExpenditureRepository;

    @Override
    public ResponseEntity<ResponseDto> postSchedule(PostScheduleRequestDto dto, String userId) {

        try {

            boolean ifExist = userRepository.existsById(userId);
            if(!ifExist) return ResponseDto.authenticationFailed();

            TravelScheduleEntity travelScheduleEntity = new TravelScheduleEntity(dto, userId);     
            travelSchduleRepository.save(travelScheduleEntity);
            Integer travelScheduleNumber = travelScheduleEntity.getTravelScheduleNumber();

            List<ExpenditureListItem> expenditureListItems = dto.getExpenditureList();
            List<TravelScheduleExpenditureEntity> travelScheduleExpenditureEntities = new ArrayList<>();
            for(ExpenditureListItem item: expenditureListItems) {
                TravelScheduleExpenditureEntity travelScheduleExpenditureEntity = new TravelScheduleExpenditureEntity(item, travelScheduleNumber);
                travelScheduleExpenditureEntities.add(travelScheduleExpenditureEntity);
            }
            travelScheduleExpenditureRepository.saveAll(travelScheduleExpenditureEntities);

            List<ScheduleListItem> scheduleListItems = dto.getScheduleList();
            List<ScheduleEntity> scheduleEntities = new ArrayList<>();
            for(ScheduleListItem item: scheduleListItems) {
                ScheduleEntity scheduleEntity = new ScheduleEntity(item, travelScheduleNumber);
                scheduleEntities.add(scheduleEntity);
            }
            scheduleRepository.saveAll(scheduleEntities);
            
        } catch(Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return ResponseDto.success();

    }

    @Override
    public ResponseEntity<? super GetScheduleListResponseDto> getScheduleList(String userId) {

        List<TravelScheduleEntity> travelScheduleEntities;

        try {
            
            travelScheduleEntities = travelSchduleRepository.findByTravelScheduleWriterId(userId);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        
        return GetScheduleListResponseDto.success(travelScheduleEntities);

    }

    @Override
    public ResponseEntity<? super GetScheduleDetailResponseDto> getScheduleDetail(String userId, Integer travelScheduleNumber) {
        
        TravelScheduleEntity travelScheduleEntity;
        List<TravelScheduleExpenditureEntity> travelScheduleExpenditureEntities;
        List<ScheduleEntity> scheduleEntities;

        try {

            travelScheduleEntity = travelSchduleRepository.findByTravelScheduleNumber(travelScheduleNumber);
            travelScheduleExpenditureEntities = travelScheduleExpenditureRepository.findByTravelScheduleNumber(travelScheduleNumber);
            scheduleEntities = scheduleRepository.findByTravelScheduleNumber(travelScheduleNumber);
            
        } catch(Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return GetScheduleDetailResponseDto.success(travelScheduleEntity, travelScheduleExpenditureEntities, scheduleEntities);

    }

    @Override
    public ResponseEntity<ResponseDto> patchSchedule(PatchScheduleRequestDto dto, String userId, Integer travelScheduleNumber) {

        try {

            TravelScheduleEntity travelScheduleEntity = travelSchduleRepository.findByTravelScheduleNumber(travelScheduleNumber);
            if (travelScheduleEntity == null) return ResponseDto.noExistBoard();

            travelScheduleEntity.update(dto);
            travelSchduleRepository.save(travelScheduleEntity);
            
            scheduleRepository.deleteByTravelScheduleNumber(travelScheduleNumber);
            travelScheduleExpenditureRepository.deleteByTravelScheduleNumber(travelScheduleNumber);

            List<ExpenditureListItem> expenditureListItems = dto.getExpenditureList();
            List<TravelScheduleExpenditureEntity> travelScheduleExpenditureEntities = new ArrayList<>();
            for(ExpenditureListItem item: expenditureListItems) {
                TravelScheduleExpenditureEntity travelScheduleExpenditureEntity = new TravelScheduleExpenditureEntity(item, travelScheduleNumber);
                travelScheduleExpenditureEntities.add(travelScheduleExpenditureEntity);
            }
            travelScheduleExpenditureRepository.saveAll(travelScheduleExpenditureEntities);

            List<ScheduleListItem> scheduleListItems = dto.getScheduleList();
            List<ScheduleEntity> scheduleEntities = new ArrayList<>();
            for(ScheduleListItem item: scheduleListItems) {
                ScheduleEntity scheduleEntity = new ScheduleEntity(item, travelScheduleNumber);
                scheduleEntities.add(scheduleEntity);
            }
            scheduleRepository.saveAll(scheduleEntities);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return ResponseDto.success();

    }

    @Override
    public ResponseEntity<ResponseDto> deleteSchedule(String userId, Integer travelScheduleNumber) {

        try {

            scheduleRepository.deleteByTravelScheduleNumber(travelScheduleNumber);
            travelScheduleExpenditureRepository.deleteByTravelScheduleNumber(travelScheduleNumber);
            travelSchduleRepository.deleteByTravelScheduleNumber(travelScheduleNumber);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return ResponseDto.success();

    }
    
}