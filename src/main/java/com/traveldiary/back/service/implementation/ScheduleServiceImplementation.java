package com.traveldiary.back.service.implementation;

import java.util.ArrayList;
import java.util.List;

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
import com.traveldiary.back.entity.UserEntity;
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
            if (!ifExist) return ResponseDto.authenticationFailed();

            UserEntity userEntity = userRepository.findByUserId(userId);
            String role = userEntity.getUserRole();
            if (role == "ROLE_ADMIN") return ResponseDto.authorizationFailed();

            TravelScheduleEntity travelScheduleEntity = new TravelScheduleEntity(dto, userId);     
            travelSchduleRepository.save(travelScheduleEntity);
            Integer travelScheduleNumber = travelScheduleEntity.getTravelScheduleNumber();

            List<ExpenditureListItem> expenditureList = dto.getExpenditureList();
            for (ExpenditureListItem item: expenditureList) {
                TravelScheduleExpenditureEntity travelScheduleExpenditureEntity = new TravelScheduleExpenditureEntity(item, travelScheduleNumber);
                travelScheduleExpenditureRepository.save(travelScheduleExpenditureEntity);
            }

            List<ScheduleListItem> scheduleList = dto.getScheduleList();
            for (ScheduleListItem item: scheduleList) {
                ScheduleEntity scheduleEntity = new ScheduleEntity(item, travelScheduleNumber);
                scheduleRepository.save(scheduleEntity);
            }
            
            
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return ResponseDto.success();
    }

    @Override
    public ResponseEntity<ResponseDto> patchSchedule(PatchScheduleRequestDto dto, String userId,
            Integer number) {
        
                try {

                    TravelScheduleEntity travelScheduleEntity = travelSchduleRepository.findByTravelScheduleNumber(number);
                    if (travelScheduleEntity == null) return ResponseDto.noExistBoard();

                    travelScheduleEntity.update(dto);
                    travelSchduleRepository.save(travelScheduleEntity);

                    List<ScheduleEntity> scheduleEntities = scheduleRepository.findByTravelScheduleNumber(number);
                    scheduleRepository.deleteAll(scheduleEntities);

                    List<TravelScheduleExpenditureEntity> travelScheduleExpenditureEntities = travelScheduleExpenditureRepository.findByTravelScheduleNumber(number);
                    travelScheduleExpenditureRepository.deleteAll(travelScheduleExpenditureEntities);

                    List<ExpenditureListItem> expenditureList = dto.getExpenditureList();
                    for (ExpenditureListItem item: expenditureList) {
                        TravelScheduleExpenditureEntity travelScheduleExpenditureEntity = new TravelScheduleExpenditureEntity(item, number);
                        travelScheduleExpenditureRepository.save(travelScheduleExpenditureEntity);
                    }

                    List<ScheduleListItem> scheduleList = dto.getScheduleListItems();
                    for (ScheduleListItem item: scheduleList) {
                        ScheduleEntity scheduleEntity = new ScheduleEntity(item, number);
                        scheduleRepository.save(scheduleEntity);
                    }

                } catch (Exception exception) {
                    exception.printStackTrace();
                    return ResponseDto.databaseError();
                }

            return ResponseDto.success();
    }

    @Override
    public ResponseEntity<ResponseDto> deleteSchedule(String userId, Integer travelScheduleNumber) {
        try {

            List<ScheduleEntity> scheduleEntities = scheduleRepository.findByTravelScheduleNumber(travelScheduleNumber);
            scheduleRepository.deleteAll(scheduleEntities);

            List<TravelScheduleExpenditureEntity> travelScheduleExpenditureEntities = travelScheduleExpenditureRepository.findByTravelScheduleNumber(travelScheduleNumber);
            travelScheduleExpenditureRepository.deleteAll(travelScheduleExpenditureEntities);

            TravelScheduleEntity travelScheduleEntity = travelSchduleRepository.findByTravelScheduleNumber(travelScheduleNumber);
            travelSchduleRepository.delete(travelScheduleEntity);

        } catch (Exception exception) {
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
    public ResponseEntity<? super GetScheduleDetailResponseDto> getScheduleDetail(String userId,
            Integer travelScheduleNumber) {
        
        try {

            TravelScheduleEntity travelScheduleEntity = travelSchduleRepository.findByTravelScheduleNumber(travelScheduleNumber);
            List<TravelScheduleExpenditureEntity> travelScheduleExpenditureEntities = travelScheduleExpenditureRepository.findByTravelScheduleNumber(travelScheduleNumber);
            List<ScheduleEntity> scheduleEntities = scheduleRepository.findByTravelScheduleNumber(travelScheduleNumber);

            return GetScheduleDetailResponseDto.success(travelScheduleEntity, travelScheduleExpenditureEntities, scheduleEntities);
            
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

    }
    
}