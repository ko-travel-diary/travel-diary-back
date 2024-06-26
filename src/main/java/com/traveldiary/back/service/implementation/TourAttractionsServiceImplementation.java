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
import com.traveldiary.back.entity.TourAttractionViewEntity;
import com.traveldiary.back.entity.TourAttractionsEntity;
import com.traveldiary.back.entity.TourAttractionsImageEntity;
import com.traveldiary.back.repository.TourAttractionViewRepository;
import com.traveldiary.back.repository.TourAttractionsImageRepository;
import com.traveldiary.back.repository.TourAttractionsRecommendRepository;
import com.traveldiary.back.repository.TourAttractionsRepository;
import com.traveldiary.back.service.TourAttractionsService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TourAttractionsServiceImplementation implements TourAttractionsService {

    private final TourAttractionsRepository tourAttractionsRepository;
    private final TourAttractionsImageRepository tourAttractionsImageRepository;
    private final TourAttractionViewRepository tourAttractionViewRepository;
    private final TourAttractionsRecommendRepository tourAttractionsRecommendRepository;

    @Override
    public ResponseEntity<ResponseDto> postTourAttractions(PostTourAttractionsRequestDto dto, String userId) {

        try{

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

    @Override
    public ResponseEntity<? super GetTourAttractionsListResponseDto> getTourAttractionsList (Double lat, Double lng) {

        List<TourAttractionViewEntity> resultSets = null;

        try {

            if (lat == null || lng == null)
                resultSets = tourAttractionViewRepository.findBy();
            else
                resultSets = tourAttractionViewRepository.getTourAttractionsRangeList(lat, lng);
                
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

    return GetTourAttractionsListResponseDto.success(resultSets);

    }

    @Override
    public ResponseEntity<? super GetSearchTourAttractionsListResponseDto> getSearchTourAttractionsList(String searchWord) {

        List<TourAttractionViewEntity> resultSets = null;

        try {

            resultSets = tourAttractionViewRepository.findByTourAttractionsNameContains(searchWord);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

    return GetSearchTourAttractionsListResponseDto.success(resultSets);

    }

    @Override
    public ResponseEntity<? super GetTourAttractionsResponseDto> getTourAttractions(Integer tourAttractionsNumber) {
        
        TourAttractionsEntity tourAttractionsEntity = null;
        List<String> tourAttractionsImageUrl = new ArrayList<>();

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
    public ResponseEntity<ResponseDto> patchTourAttractions(PatchTourAttrcationsRequestDto dto, Integer tourAttractionsNumber, String userId) {

        try {

            tourAttractionsImageRepository.deleteByTourAttractionsNumber(tourAttractionsNumber);

            TourAttractionsEntity tourAttractionsEntity = tourAttractionsRepository.findByTourAttractionsNumber(tourAttractionsNumber);
            if (tourAttractionsEntity == null) return ResponseDto.noExistData();

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


    @Override
    public ResponseEntity<ResponseDto> deleteTourAttractions(Integer tourAttractionsNumber, String userId) {

        try {

            tourAttractionsRecommendRepository.deleteByTourAttractionsNumber(tourAttractionsNumber);
            tourAttractionsImageRepository.deleteByTourAttractionsNumber(tourAttractionsNumber);
            tourAttractionsRepository.deleteByTourAttractionsNumber(tourAttractionsNumber);
            
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return ResponseDto.success();

    }

}