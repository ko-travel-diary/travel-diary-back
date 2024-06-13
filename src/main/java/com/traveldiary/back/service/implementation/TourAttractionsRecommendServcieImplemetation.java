package com.traveldiary.back.service.implementation;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.traveldiary.back.dto.response.ResponseDto;
import com.traveldiary.back.dto.response.touarAttraction.GetTourAttractionsRecommendResponseDto;
import com.traveldiary.back.entity.TourAttractionsEntity;
import com.traveldiary.back.entity.TourAttractionsRecommendEntity;
import com.traveldiary.back.repository.TourAttractionsRecommendRepository;
import com.traveldiary.back.repository.TourAttractionsRepository;
import com.traveldiary.back.repository.UserRepository;
import com.traveldiary.back.service.TourAttractionsRecommendService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TourAttractionsRecommendServcieImplemetation implements TourAttractionsRecommendService{

    private final UserRepository userRepository;
    private final TourAttractionsRepository tourAttractionsRepository;
    private final TourAttractionsRecommendRepository tourAttractionsRecommendRepository;

    @Override
    public ResponseEntity<? super GetTourAttractionsRecommendResponseDto> getTourRecommendStatus(Integer tourAttractionNumber, String userId) {

        boolean existsRecommend = false;

        try{

            if(tourAttractionNumber == null) return ResponseDto.noExistBoard();
            if(userId == null) return ResponseDto.authenticationFailed();

            existsRecommend = tourAttractionsRecommendRepository.existsByUserIdAndTourAttractionsNumber(userId, tourAttractionNumber);

        }catch(Exception exception){
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return GetTourAttractionsRecommendResponseDto.success(existsRecommend);

    }

    @Override
    public ResponseEntity<ResponseDto> patchTourRecommend(Integer tourAttractionNumber, String userId) {

        try{

            TourAttractionsEntity tourAttractionsEntity = tourAttractionsRepository.findByTourAttractionsNumber(tourAttractionNumber);
            if(tourAttractionsEntity == null) return ResponseDto.noExistData();

            boolean existId = userRepository.existsByUserId(userId);
            if(!existId) return ResponseDto.noExistUser();

            boolean isRecommendedId = tourAttractionsRecommendRepository.existsByUserIdAndTourAttractionsNumber(userId, tourAttractionNumber);

            TourAttractionsRecommendEntity tourAttractionRecommendEntity = new TourAttractionsRecommendEntity(tourAttractionNumber, userId);

            if(isRecommendedId){
                tourAttractionsRecommendRepository.delete(tourAttractionRecommendEntity);
                tourAttractionsEntity.decreaseRecommendCount();
            }
            else{
                tourAttractionsRecommendRepository.save(tourAttractionRecommendEntity);
                tourAttractionsEntity.increaseRecommendCount();
            }

            tourAttractionsRepository.save(tourAttractionsEntity);

        }catch(Exception exception){
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return ResponseDto.success();

    }
    
}
