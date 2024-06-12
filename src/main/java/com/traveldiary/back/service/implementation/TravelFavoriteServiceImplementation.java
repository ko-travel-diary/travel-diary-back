package com.traveldiary.back.service.implementation;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.traveldiary.back.dto.response.ResponseDto;
import com.traveldiary.back.dto.response.review.GetTravelReviewFavoriteStatusResponseDto;
import com.traveldiary.back.entity.TravelFavoriteEntity;
import com.traveldiary.back.entity.TravelReviewEntity;
import com.traveldiary.back.repository.TravelFavoriteRepository;
import com.traveldiary.back.repository.TravelReviewRepository;
import com.traveldiary.back.repository.UserRepository;
import com.traveldiary.back.service.TravelFavoriteService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TravelFavoriteServiceImplementation implements TravelFavoriteService {

    private final UserRepository userRepository;
    private final TravelReviewRepository travelReviewRepository;
    private final TravelFavoriteRepository travelFavoriteRepository;

    @Override
    public ResponseEntity<? super GetTravelReviewFavoriteStatusResponseDto> getFavoriteStatus(Integer reviewNumber, String userId) {

        boolean existsFavorite = false;
        
        try {

            if(reviewNumber == null) return ResponseDto.noExistBoard();
            if(userId == null) return ResponseDto.authenticationFailed();
            
            existsFavorite = travelFavoriteRepository.existsByUserIdAndReviewNumber(userId, reviewNumber);

        } catch(Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return GetTravelReviewFavoriteStatusResponseDto.success(existsFavorite);

    }

    @Override
    public ResponseEntity<ResponseDto> patchTravelFavorite(Integer reviewNumber, String userId) {
        
        try {

            TravelReviewEntity travelReviewEntity = travelReviewRepository.findByReviewNumber(reviewNumber);
            if(travelReviewEntity == null) return ResponseDto.noExistBoard();

            boolean existId = userRepository.existsByUserId(userId);
            if(!existId) return ResponseDto.noExistUser();

            boolean isfavoriteId = travelFavoriteRepository.existsByUserIdAndReviewNumber(userId, reviewNumber);

            TravelFavoriteEntity travelFavoriteEntity = new TravelFavoriteEntity(userId, reviewNumber);

            if(isfavoriteId) {
                travelFavoriteRepository.delete(travelFavoriteEntity);
                travelReviewEntity.decreaseFavoriteCount();
            }
            else {
                travelFavoriteRepository.save(travelFavoriteEntity);
                travelReviewEntity.increaseFavoriteCount();
            }
            
            travelReviewRepository.save(travelReviewEntity);

        } catch(Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return ResponseDto.success();

    }

}