package com.traveldiary.back.service.implementation;

import java.util.ArrayList;
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
import com.traveldiary.back.dto.response.review.PostTravelReviewResponseDto;
import com.traveldiary.back.entity.TravelReviewEntity;
import com.traveldiary.back.entity.TravelReviewImageEntity;
import com.traveldiary.back.entity.TravelReviewViewEntity;
import com.traveldiary.back.entity.UserEntity;
import com.traveldiary.back.repository.TravelFavoriteRepository;
import com.traveldiary.back.repository.TravelReviewImageRepository;
import com.traveldiary.back.repository.TravelReviewRepository;
import com.traveldiary.back.repository.TravelReviewViewRepository;
import com.traveldiary.back.repository.UserRepository;
import com.traveldiary.back.service.TravelReviewService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TravelReviewServiceImplementation implements TravelReviewService{

    private final UserRepository userRepository;
    private final TravelReviewRepository travelReviewRepository;
    private final TravelReviewViewRepository travelReviewViewRepository;
    private final TravelReviewImageRepository travelReviewImageRepository;
    private final TravelFavoriteRepository travelFavoriteRepository;

    @Override
    public ResponseEntity<? super PostTravelReviewResponseDto> postTravelReview(PostTravelReviewRequestDto dto, String userId) {

        Integer travelReviewNumber = null;

        try {

            boolean isExistUser = userRepository.existsByUserId(userId);
            if(!isExistUser) return ResponseDto.authenticationFailed();

            Integer travelScheduleNumber = dto.getTravelScheduleNumber();
            if(travelScheduleNumber == 0) dto.setTravelScheduleNumber(null);
            
            TravelReviewEntity travelReviewEntity = new TravelReviewEntity(dto, userId);
            travelReviewRepository.save(travelReviewEntity);
            
            travelReviewNumber = travelReviewEntity.getReviewNumber();

            List<String> images = dto.getTravelReviewImageUrl();
            for(String image: images) {
                TravelReviewImageEntity imageEntity = new TravelReviewImageEntity(travelReviewNumber, image);
                travelReviewImageRepository.save(imageEntity);
            }
            
        } catch(Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return PostTravelReviewResponseDto.success(travelReviewNumber);

    }

    @Override
    public ResponseEntity<? super GetTravelReviewBoardResponseDto> getReviewBoardList() {

        List<TravelReviewViewEntity> travelReviewViewEntities = new ArrayList<>();
        
        try {

            travelReviewViewEntities = travelReviewViewRepository.findBy();

        } catch(Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return GetTravelReviewBoardResponseDto.success(travelReviewViewEntities);

    }

    @Override
    public ResponseEntity<? super GetTravelReviewSearchResponseDto> getSearchList(String titleAndContent, String writer, String writedate) {
        
        List<TravelReviewViewEntity> travelReviewViewEntities = new ArrayList<>();

        try {

            if(!titleAndContent.isEmpty()) travelReviewViewEntities = travelReviewViewRepository.findByReviewTitleContainsOrReviewContentContains(titleAndContent, titleAndContent);
        
            if(!writer.isEmpty()) travelReviewViewEntities = travelReviewViewRepository.findByReviewWriterIdContains(writer);

            if(!writedate.isEmpty()) travelReviewViewEntities = travelReviewViewRepository.findByReviewDatetimeContains(writedate); 

        } catch(Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return GetTravelReviewSearchResponseDto.success(travelReviewViewEntities);

    }

    @Override
    public ResponseEntity<? super GetTravelReviewDetailResponseDto> getReview(Integer reviewNumber) {
        
        TravelReviewEntity travelReviewEntity = null;
        List<String> travelReviewImageUrl = new ArrayList<>();

        try {

            travelReviewEntity = travelReviewRepository.findByReviewNumber(reviewNumber);
            if(travelReviewEntity == null) return ResponseDto.noExistBoard();

            travelReviewImageUrl = new ArrayList<>();
            List<TravelReviewImageEntity> reviewImageEntities = travelReviewImageRepository.findByTravelReviewNumber(reviewNumber);
            for(TravelReviewImageEntity entity: reviewImageEntities){
                String image = entity.getTravelReviewImageUrl();
                travelReviewImageUrl.add(image);
            }
            
        } catch(Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return GetTravelReviewDetailResponseDto.success(travelReviewEntity, travelReviewImageUrl);

    }

    @Override
    public ResponseEntity<? super GetTravelReviewMyListResponseDto> getReviewMyList(String userId) {

        List<TravelReviewViewEntity> travelReviewViewEntities = new ArrayList<>();
        
        try {

            boolean isExistUser = userRepository.existsByUserId(userId);
            if(!isExistUser) return ResponseDto.authenticationFailed();
            
            travelReviewViewEntities = travelReviewViewRepository.findByReviewWriterId(userId);
            if(travelReviewViewEntities == null) return ResponseDto.authorizationFailed();
            
        } catch(Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return GetTravelReviewMyListResponseDto.success(travelReviewViewEntities);

    }

    
    @Override
    public ResponseEntity<? super GetTravelReviewSearchResponseDto> getReviewMyListSearchList(String searchWord, String userId) {

        List<TravelReviewViewEntity> travelReviewViewEntities = new ArrayList<>();

        try {

            boolean isExistUser = userRepository.existsByUserId(userId);
            if(!isExistUser) return ResponseDto.authenticationFailed();
        
            travelReviewViewEntities = travelReviewViewRepository.findByReviewTitleContains(searchWord);
            

        } catch(Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return GetTravelReviewSearchResponseDto.success(travelReviewViewEntities);
        
    }

    @Override
    public ResponseEntity<ResponseDto> patchTravelReview(PatchTravelReviewRequestDto dto, Integer reviewNumber, String userId) {
        
        try {

            TravelReviewEntity travelReviewEntity = travelReviewRepository.findByReviewNumber(reviewNumber);
            if(travelReviewEntity == null) return ResponseDto.noExistBoard();

            String writerId = travelReviewEntity.getReviewWriterId();
            boolean iswriterId = userId.equals(writerId);
            if(!iswriterId) return ResponseDto.authorizationFailed();

            Integer travelScheduleNumber = dto.getTravelScheduleNumber();
            if(travelScheduleNumber == 0) dto.setTravelScheduleNumber(null);

            travelReviewEntity.update(dto);
            travelReviewRepository.save(travelReviewEntity);

            List<TravelReviewImageEntity> travelReviewImageEntities = travelReviewImageRepository.findByTravelReviewNumber(reviewNumber);
            travelReviewImageRepository.deleteAll(travelReviewImageEntities);

            List<String> images = dto.getTravelReviewImageUrl();
            for(String image: images) {
                TravelReviewImageEntity imageEntity = new TravelReviewImageEntity(reviewNumber, image);
                travelReviewImageRepository.save(imageEntity);
            }

        } catch(Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return ResponseDto.success();

    }

    @Override
    public ResponseEntity<ResponseDto> patchTravelView(Integer reviewNumber) {

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
    public ResponseEntity<ResponseDto> deleteTravelReview(Integer reviewNumber, String userId) {
        
        try {

            TravelReviewEntity travelReviewEntity = travelReviewRepository.findByReviewNumber(reviewNumber);
            if(travelReviewEntity == null) return ResponseDto. noExistBoard();

            String writerId = travelReviewEntity.getReviewWriterId();
            boolean iswriterId = userId.equals(writerId);

            UserEntity userEntity = userRepository.findByUserId(userId);
            String userRole = userEntity.getUserRole();
            boolean isAdmin = userRole.equals("ROLE_ADMIN");

            if(!iswriterId && !isAdmin) return ResponseDto.authenticationFailed();

            travelReviewImageRepository.deleteByTravelReviewNumber(reviewNumber);
            travelFavoriteRepository.deleteByReviewNumber(reviewNumber);
            travelReviewRepository.delete(travelReviewEntity);

        } catch(Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return ResponseDto.success();

    }

}