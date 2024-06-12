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
import com.traveldiary.back.entity.TravelFavoriteEntity;
import com.traveldiary.back.entity.TravelReviewEntity;
import com.traveldiary.back.entity.TravelReviewImageEntity;
import com.traveldiary.back.entity.UserEntity;
import com.traveldiary.back.repository.TravelFavoriteRepository;
import com.traveldiary.back.repository.TravelReviewImageRepository;
import com.traveldiary.back.repository.TravelReviewRepository;
import com.traveldiary.back.repository.TravelReviewViewRepository;
import com.traveldiary.back.repository.UserRepository;
import com.traveldiary.back.repository.resultSet.GetTravelReviewResultSet;
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
    public ResponseEntity<? super GetTravelReviewBoardResponseDto> getReviewBoardList() {

        List<GetTravelReviewResultSet> resultSets = new ArrayList<>();
        
        try {

            resultSets = travelReviewViewRepository.getReviewBoardList();

        } catch(Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return GetTravelReviewBoardResponseDto.success(resultSets);

    }

    @Override
    public ResponseEntity<? super GetTravelReviewSearchResponseDto> getReviewTitleAndContentSearchList(String searchWord) {
        
        List<GetTravelReviewResultSet> resultSets = new ArrayList<>();

        try {
        
            resultSets = travelReviewViewRepository.getReivewTitleOrReviewContent(searchWord);
            
        } catch(Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        
        return GetTravelReviewSearchResponseDto.success(resultSets);

    }

    @Override
    public ResponseEntity<? super GetTravelReviewSearchResponseDto> getReviewWriterSearchList(String searchWord) {

        List<GetTravelReviewResultSet> resultSets = new ArrayList<>();

        try {
        
            resultSets = travelReviewViewRepository.getReviewWriter(searchWord);
            
        } catch(Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        
        return GetTravelReviewSearchResponseDto.success(resultSets);

    }

    @Override
    public ResponseEntity<? super GetTravelReviewSearchResponseDto> getReviewWriteDateSearchList(String searchWord) {

        List<GetTravelReviewResultSet> resultSets = new ArrayList<>();

        try {
        
            resultSets = travelReviewViewRepository.getReviewDatetime(searchWord);
            
        } catch(Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        
        return GetTravelReviewSearchResponseDto.success(resultSets);

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

        List<GetTravelReviewResultSet> resultSets = new ArrayList<>();
        
        try {

            boolean isExistUser = userRepository.existsByUserId(userId);
            if(!isExistUser) return ResponseDto.authenticationFailed();
            
            resultSets = travelReviewViewRepository.findByReviewWriterIdOrderByReviewNumberDesc(userId);
            if(resultSets == null) return ResponseDto.authorizationFailed();
            
        } catch(Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return GetTravelReviewMyListResponseDto.success(resultSets);

    }

    
    @Override
    public ResponseEntity<? super GetTravelReviewSearchResponseDto> getReviewMyListSearchList(String searchWord, String userId) {

        List<GetTravelReviewResultSet> resultSets = new ArrayList<>();

        try {

            boolean isExistUser = userRepository.existsByUserId(userId);
            if(!isExistUser) return ResponseDto.authenticationFailed();
        
            resultSets = travelReviewViewRepository.findByReviewTitleContainsOrderByReviewNumberDesc(searchWord);
            

        } catch(Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return GetTravelReviewSearchResponseDto.success(resultSets);
        
    }

    @Override
    public ResponseEntity<? super PostTravelReviewResponseDto> postTravelReview(PostTravelReviewRequestDto dto, String userId) {

        Integer travelReviewNumber = null;

        try {

            boolean isExistUser = userRepository.existsByUserId(userId);
            if(!isExistUser) return ResponseDto.authenticationFailed();
            
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
    public ResponseEntity<ResponseDto> patchTravelReview(PatchTravelReviewRequestDto dto, Integer reviewNumber, String userId) {
        
        try {

            TravelReviewEntity travelReviewEntity = travelReviewRepository.findByReviewNumber(reviewNumber);
            if(travelReviewEntity == null) return ResponseDto.noExistBoard();

            String writerId = travelReviewEntity.getReviewWriterId();
            boolean iswriterId = userId.equals(writerId);
            if(!iswriterId) return ResponseDto.authorizationFailed();

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

            List<TravelReviewImageEntity> travelReviewImageEntities = travelReviewImageRepository.findByTravelReviewNumber(reviewNumber);
            travelReviewImageRepository.deleteAll(travelReviewImageEntities);
            
            List<TravelFavoriteEntity> travelFavoriteEntities = travelFavoriteRepository.findByReviewNumber(reviewNumber);
            travelFavoriteRepository.deleteAll(travelFavoriteEntities);

            travelReviewRepository.delete(travelReviewEntity);

        } catch(Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return ResponseDto.success();

    }

}
