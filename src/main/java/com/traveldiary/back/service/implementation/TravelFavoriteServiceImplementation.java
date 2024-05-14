package com.traveldiary.back.service.implementation;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.traveldiary.back.dto.request.review.PatchTravelFavoriteReviewRequestDto;
import com.traveldiary.back.dto.response.ResponseDto;
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
    public ResponseEntity<ResponseDto> travelFavorite(PatchTravelFavoriteReviewRequestDto dto, int reviewNumber,
            String userId) {
        
        try {

            TravelReviewEntity travelReviewEntity = travelReviewRepository.findByReviewNumber(reviewNumber);
            if(travelReviewEntity == null) return ResponseDto.noExistBoard();

            boolean existId = userRepository.existsByUserId(userId);
            if(!existId) return ResponseDto.notFound();

            boolean isfavoriteId = travelFavoriteRepository.existsByUserIdAndReviewNumber(userId, reviewNumber);

            TravelFavoriteEntity travelFavoriteEntity = new TravelFavoriteEntity(userId, reviewNumber);

            if(isfavoriteId) {
                travelFavoriteRepository.delete(travelFavoriteEntity);
                travelReviewEntity.decreaseViewCount();
            }
            else {
                travelFavoriteRepository.save(travelFavoriteEntity);
                travelReviewEntity.increaseViewCount();
            }
            
            travelReviewRepository.save(travelReviewEntity);

        } catch(Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return ResponseDto.success();
    }
    
}
