package com.traveldiary.back.service.implementation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.traveldiary.back.dto.request.tourAttractions.PatchTourAttrcationsRequestDto;
import com.traveldiary.back.dto.request.tourAttractions.PostTourAttractionsRequestDto;
import com.traveldiary.back.dto.response.ResponseDto;
import com.traveldiary.back.dto.response.touarAttraction.GetSearchTourAttractionsListResponseDto;
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
    public ResponseEntity<? super GetTourAttractionsListResponseDto> getTourAttractionsList (Double lat, Double lng) {

        List<GetTourAttractionsResultSet> resultSets = null;

        try {
            if (lat == null || lng == null)
                resultSets = tourAttractionsRepository.getTourAttractionsList();
            else
                resultSets = tourAttractionsRepository.getTourAttractionsRangeList(lat, lng);
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
    return GetTourAttractionsListResponseDto.success(resultSets);
    }

    @Override
    public ResponseEntity<? super GetSearchTourAttractionsListResponseDto> getSearchTourAttractionsList(String searchWord) {

        List<GetTourAttractionsResultSet> resultSets = null;

        try {

            resultSets = tourAttractionsRepository.getSearchTourAttractionsList(searchWord);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
    return GetSearchTourAttractionsListResponseDto.success(resultSets);
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
            if(images.isEmpty() || images.get(0) == null) {
                String image = "https://cdn-icons-png.flaticon.com/128/11423/11423562.png";
                TourAttractionsImageEntity imageEntity = new TourAttractionsImageEntity(tourAttractionsNumber, image);
                tourAttractionsImageRepository.save(imageEntity);
            }
            
        }catch(Exception exception){
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        return ResponseDto.success();
    }

    @Override
    public ResponseEntity<ResponseDto> deleteTourAttractions(Integer tourAttractionsNumber, String userId) {
        
        UserEntity userEntity;
        TourAttractionsEntity tourAttractionsEntity;
        List<TourAttractionsImageEntity> tourAttractionsImageEntities;

        try {

            userEntity = userRepository.findByUserId(userId);
            String role = userEntity.getUserRole();
            if (role == "ROLE_USER") return ResponseDto.authorizationFailed();

            tourAttractionsImageEntities = tourAttractionsImageRepository.findByTourAttractionsNumber(tourAttractionsNumber);
            tourAttractionsImageRepository.deleteAll(tourAttractionsImageEntities);

            tourAttractionsEntity = tourAttractionsRepository.findByTourAttractionsNumber(tourAttractionsNumber);
            tourAttractionsRepository.delete(tourAttractionsEntity);
            
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return ResponseDto.success();
    }

    @Override
    public ResponseEntity<ResponseDto> patchTourAttractions(PatchTourAttrcationsRequestDto dto,
            Integer tourAttractionsNumber, String userId) {
        
        UserEntity userEntity;
        TourAttractionsEntity tourAttractionsEntity;
        List<TourAttractionsImageEntity> tourAttractionsImageEntities;

        try {

            userEntity = userRepository.findByUserId(userId);
            String userRole = userEntity.getUserRole();
            if (userRole == "ROLE_USER") return ResponseDto.authorizationFailed();

            tourAttractionsImageEntities = tourAttractionsImageRepository.findByTourAttractionsNumber(tourAttractionsNumber);
            tourAttractionsImageRepository.deleteAll(tourAttractionsImageEntities);

            tourAttractionsEntity = tourAttractionsRepository.findByTourAttractionsNumber(tourAttractionsNumber);
            if (tourAttractionsEntity == null) return ResponseDto.noExistData();

            System.out.println(dto.getTourAttractionsImageUrl());

            tourAttractionsEntity.update(dto);
            tourAttractionsRepository.save(tourAttractionsEntity);

            List<String> images = dto.getTourAttractionsImageUrl();
            for (String image : images) {
                TourAttractionsImageEntity tourAttractionsImageEntity = new TourAttractionsImageEntity(tourAttractionsNumber, image);
                tourAttractionsImageRepository.save(tourAttractionsImageEntity);
            }
            
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

    return ResponseDto.success();
    }


}