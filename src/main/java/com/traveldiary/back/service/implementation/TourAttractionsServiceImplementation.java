package com.traveldiary.back.service.implementation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.traveldiary.back.dto.request.tourAttractions.PostTourAttractionsRequestDto;
import com.traveldiary.back.dto.response.ResponseDto;
import com.traveldiary.back.dto.response.touarAttraction.GetTourAttractionsListResponseDto;
import com.traveldiary.back.dto.response.touarAttraction.GetTourAttractionsResponseDto;
import com.traveldiary.back.entity.TourAttractionsEntity;
import com.traveldiary.back.entity.TourAttractionsImageEntity;
import com.traveldiary.back.entity.UserEntity;
import com.traveldiary.back.repository.TourAttractionsImageRepository;
import com.traveldiary.back.repository.TourAttractionsRepository;
import com.traveldiary.back.repository.UserRepository;
import com.traveldiary.back.repository.resultSet.GetTourAttractionsResultSet;
import com.traveldiary.back.service.TourAttractionsService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TourAttractionsServiceImplementation implements TourAttractionsService {

    private final TourAttractionsRepository tourAttractionsRepository;
    private final TourAttractionsImageRepository tourAttractionsImageRepository;
    private final UserRepository userRepository;

    @Override
    public ResponseEntity<? super GetTourAttractionsListResponseDto> getTourAttractionsList () {

        List<GetTourAttractionsResultSet> resultSets = null;

        try {
            resultSets = tourAttractionsRepository.getTourAttractionsList();
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
    return GetTourAttractionsListResponseDto.success(resultSets);
    }

    @Override
    public ResponseEntity<? super GetTourAttractionsResponseDto> getTourAttractions(Integer tourAttractionsNumber) {
        TourAttractionsEntity tourAttractionsEntity;
        List<String> tourAttractionsImageUrl;
        try{
            
            tourAttractionsEntity = tourAttractionsRepository.findByTourAttractionsNumber(tourAttractionsNumber);
            if(tourAttractionsEntity == null) return ResponseDto.noExistData();

            tourAttractionsImageUrl = new ArrayList<>();
            List<TourAttractionsImageEntity> tourAttractionsImageEntities = tourAttractionsImageRepository.findByTourAttractionsNumber(tourAttractionsNumber);
            for(TourAttractionsImageEntity entity : tourAttractionsImageEntities){
                String image = entity.getTourAttractionsImageUrl();
                tourAttractionsImageUrl.add(image);
            }
        }catch(Exception exception){
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        return GetTourAttractionsResponseDto.success(tourAttractionsEntity, tourAttractionsImageUrl);
    }

    @Override
    public ResponseEntity<ResponseDto> postTourAttractions(PostTourAttractionsRequestDto dto, String userId) {

        try{

            UserEntity userEntity = userRepository.findByUserId(userId);
            String userRole = userEntity.getUserRole();
            System.out.println(userRole);
            if(userRole == "ROLE_USER") return ResponseDto.authenticationFailed();

            String tourAttractionsName = dto.getTourAttractionsName();
            boolean existsed = tourAttractionsRepository.existsByTourAttractionsName(tourAttractionsName);
            if(existsed) return ResponseDto.duplicatedTourAttractions();

            TourAttractionsEntity tourAttractionsEntity = new TourAttractionsEntity(dto);
            tourAttractionsRepository.save(tourAttractionsEntity);

            int tourAttractionsNumber = tourAttractionsEntity.getTourAttractionsNumber();

            List<String> images = dto.getTourAttractionsImageUrl();
            for (String image : images) {
                TourAttractionsImageEntity imageEntity = new TourAttractionsImageEntity(tourAttractionsNumber, image);
                tourAttractionsImageRepository.save(imageEntity);
            }
            

        }catch(Exception exception){
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        return ResponseDto.success();
    }

}