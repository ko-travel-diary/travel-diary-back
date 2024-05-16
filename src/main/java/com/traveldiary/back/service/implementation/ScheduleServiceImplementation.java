package com.traveldiary.back.service.implementation;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.traveldiary.back.common.object.ExpenditureListItem;
import com.traveldiary.back.common.object.ScheduleListItem;
import com.traveldiary.back.dto.request.schedule.PatchScheduleRequestDto;
import com.traveldiary.back.dto.request.schedule.PostScheduleRequestDto;
import com.traveldiary.back.dto.response.ResponseDto;
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
            if (!ifExist) return ResponseDto.authenticationFailed();

            TravelScheduleEntity travelScheduleEntity = new TravelScheduleEntity(dto, userId);     
            travelSchduleRepository.save(travelScheduleEntity);
            Integer travelScheduleNumber = travelScheduleEntity.getTravelScheduleNumber();

            List<ExpenditureListItem> expenditureList = dto.getExpenditureList();
            for (ExpenditureListItem item: expenditureList) {
                TravelScheduleExpenditureEntity travelScheduleExpenditureEntity = new TravelScheduleExpenditureEntity(item, travelScheduleNumber);
                travelScheduleExpenditureRepository.save(travelScheduleExpenditureEntity);
            }

            List<ScheduleListItem> scheduleList = dto.getScheduleListItems();
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

                    Integer travelScheduleNumber = travelScheduleEntity.getTravelScheduleNumber();

                    // TravelScheduleExpenditureEntity travelScheduleExpenditureEntity = travelScheduleExpenditureRepository.findByTravelScheduleNumber(travelScheduleNumber);
                    // List<ExpenditureListItem> expenditureList = dto.getExpenditureList();
                    // for (ExpenditureListItem item: expenditureList) {
                    //     travelScheduleExpenditureEntity.
                    //     travelScheduleExpenditureEntity.
                    //     travelScheduleExpenditureRepository.save(travelScheduleExpenditureEntity);
                    // }
                    
                    
                    ScheduleEntity scheduleEntity = scheduleRepository.findByTravelScheduleNumber(travelScheduleNumber);
                    List<ScheduleListItem> scheduleList = dto.getScheduleListItems();
                    
                } catch (Exception exception) {
                    exception.printStackTrace();
                    return ResponseDto.databaseError();
                }

            return ResponseDto.success();
    }
    
}
