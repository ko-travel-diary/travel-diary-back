package com.traveldiary.back.service.implementation;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.traveldiary.back.dto.request.review.PatchTravelReviewRequestDto;
import com.traveldiary.back.dto.request.review.PostTravelReviewRequestDto;
import com.traveldiary.back.dto.response.ResponseDto;
import com.traveldiary.back.dto.response.review.GetTravelReviewBoardResponseDto;
import com.traveldiary.back.dto.response.review.GetTravelReviewDetailResponseDto;
import com.traveldiary.back.dto.response.review.GetTravelReviewMyListResponseDto;
import com.traveldiary.back.dto.response.review.GetTravelReviewSearchResponseDto;
import com.traveldiary.back.entity.TravelReviewEntity;
import com.traveldiary.back.repository.TravelReviewRepository;
import com.traveldiary.back.repository.UserRepository;
import com.traveldiary.back.service.TravelReviewService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TravelReviewServiceImplementation implements TravelReviewService{

    private final UserRepository userRepository;
    private final TravelReviewRepository travelReviewRepository;

    @Override
    public ResponseEntity<? super GetTravelReviewBoardResponseDto> getReviewBoardList() {
        
        try {

            List<TravelReviewEntity> travelReviewEntities = travelReviewRepository.findByOrderByReviewNumberDesc();
            return GetTravelReviewBoardResponseDto.success(travelReviewEntities);


        } catch(Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
    }

    @Override
    public ResponseEntity<? super GetTravelReviewSearchResponseDto> getReviewSearchList(String searchWord) {
        
        try {
        
            List<TravelReviewEntity> travelReviewEntities = travelReviewRepository.findByReviewTitleContainsOrderByReviewNumberDesc(searchWord);
            return GetTravelReviewSearchResponseDto.success(travelReviewEntities);
            
        } catch(Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
    }

    @Override
    public ResponseEntity<? super GetTravelReviewDetailResponseDto> getReview(int reviewNumber) {
        
        try {

            TravelReviewEntity travelReviewEntity = travelReviewRepository.findByReviewNumber(reviewNumber);
            if(travelReviewEntity == null) return ResponseDto.noExistBoard();

            return GetTravelReviewDetailResponseDto.success(travelReviewEntity);

        } catch(Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
    }

    @Override
    public ResponseEntity<? super GetTravelReviewMyListResponseDto> getReviewMyList(String userId) {
        
        try {
            
            List<TravelReviewEntity> travelReviewEntities = travelReviewRepository.findByReviewWriterId(userId);
            if(travelReviewEntities == null) return ResponseDto.authorizationFailed();

            return GetTravelReviewMyListResponseDto.success(travelReviewEntities);
            
        } catch(Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
    }

    @Override
    public ResponseEntity<ResponseDto> postTravelReview(PostTravelReviewRequestDto dto, String userId) {
        
        try {

            boolean isExistUser = userRepository.existsByUserId(userId);
            if(!isExistUser) return ResponseDto.authenticationFailed();
            
            TravelReviewEntity travelReviewEntity = new TravelReviewEntity(dto, userId);
            travelReviewRepository.save(travelReviewEntity);

        } catch(Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return ResponseDto.success();
    }

    @Override
    public ResponseEntity<ResponseDto> patchTravelReview(PatchTravelReviewRequestDto dto, int reviewNumber,
            String userId) {
        
        try {

            TravelReviewEntity travelReviewEntity = travelReviewRepository.findByReviewNumber(reviewNumber);
            if(travelReviewEntity == null) return ResponseDto.noExistBoard();

            String writerId = travelReviewEntity.getReviewWriterId();
            boolean iswriterId = userId.equals(writerId);
            if(!iswriterId) return ResponseDto.authorizationFailed();

            travelReviewEntity.update(dto);
            travelReviewRepository.save(travelReviewEntity);

        } catch(Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        return ResponseDto.success();
    }

    @Override
    public ResponseEntity<ResponseDto> patchTravelView(int reviewNumber) {

        try {

            TravelReviewEntity travelReviewEntity = travelReviewRepository.findByReviewNumber(reviewNumber);
            if(travelReviewEntity == null) return ResponseDto.noExistBoard();

            travelReviewEntity.increaseViewCount();
            travelReviewRepository.save(travelReviewEntity);

        } catch(Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return ResponseDto.success();
    }

    @Override
    public ResponseEntity<ResponseDto> deleteTravelReview(int reviewNumber, String userId) {
        
        try {

            TravelReviewEntity travelReviewEntity = travelReviewRepository.findByReviewNumber(reviewNumber);
            if(travelReviewEntity == null) return ResponseDto. noExistBoard();

            String writerId = travelReviewEntity.getReviewWriterId();
            boolean iswriterId = userId.equals(writerId);
            if(!iswriterId) return ResponseDto.authorizationFailed();

            travelReviewRepository.save(travelReviewEntity);

        } catch(Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return ResponseDto.success();
    }
}
