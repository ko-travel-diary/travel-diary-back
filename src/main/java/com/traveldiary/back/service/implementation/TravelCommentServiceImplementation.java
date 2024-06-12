package com.traveldiary.back.service.implementation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.traveldiary.back.dto.request.review.PatchTravelCommentRequestDto;
import com.traveldiary.back.dto.request.review.PostTravelCommentRequestDto;
import com.traveldiary.back.dto.response.ResponseDto;
import com.traveldiary.back.dto.response.review.GetTravelReviewCommentListResponseDto;
import com.traveldiary.back.entity.TravelCommentEntity;
import com.traveldiary.back.repository.TravelCommentRepository;
import com.traveldiary.back.repository.TravelReviewRepository;
import com.traveldiary.back.repository.UserRepository;
import com.traveldiary.back.service.TravelCommentService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TravelCommentServiceImplementation implements TravelCommentService{

    private final UserRepository userRepository;
    private final TravelReviewRepository travelReviewRepository;
    private final TravelCommentRepository travelCommentRepository;

    @Override
    public ResponseEntity<ResponseDto> postTravelComment(PostTravelCommentRequestDto dto, Integer reviewNumber, String userId) {
        
        try {

            boolean existUser = userRepository.existsByUserId(userId);
            if(!existUser) return ResponseDto.authorizationFailed();

            boolean existReview = travelReviewRepository.existsByReviewNumber(reviewNumber);
            if(!existReview) return ResponseDto.noExistBoard();

            Integer parentsNumber = dto.getCommentParentsNumber();
            if (parentsNumber != null) {
                boolean existComment = travelCommentRepository.existsByCommentNumber(parentsNumber);
                if(!existComment) return ResponseDto.noExistComment();
            }
            
            TravelCommentEntity travelCommentEntity = new TravelCommentEntity(dto, reviewNumber, userId);

            travelCommentRepository.save(travelCommentEntity);

        } catch(Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return ResponseDto.success();

    }

    @Override
    public ResponseEntity<? super GetTravelReviewCommentListResponseDto> getTravelCommentList(Integer reviewNumber) {

        List<TravelCommentEntity> travelCommentEntities = new ArrayList<>();
        
        try {
        
            travelCommentEntities = travelCommentRepository.findByCommentReviewNumber(reviewNumber);
            if(travelCommentEntities == null) return ResponseDto.noExistBoard();

        } catch(Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return GetTravelReviewCommentListResponseDto.success(travelCommentEntities);

    }

    @Override
    public ResponseEntity<ResponseDto> patchTravelComment(PatchTravelCommentRequestDto dto, Integer commentNumber, Integer reviewNumber, String userId) {
        
        try {

            boolean existReview = travelReviewRepository.existsByReviewNumber(reviewNumber);
            if(!existReview) return ResponseDto.noExistBoard();
            
            TravelCommentEntity travelCommentEntity = travelCommentRepository.findByCommentNumber(commentNumber);
            if(travelCommentEntity == null) return ResponseDto.noExistComment();

            String writerId = travelCommentEntity.getCommentWriterId();
            boolean iswriterId = userId.equals(writerId);
            if(!iswriterId) return ResponseDto.authorizationFailed();
            
            travelCommentEntity.update(dto);
            travelCommentRepository.save(travelCommentEntity);

        } catch(Exception exception) {
            return ResponseDto.databaseError();
        }

        return ResponseDto.success();

    }

    @Override
    public ResponseEntity<ResponseDto> deleteTravelComment(Integer commentNumber, Integer reviewNumber, String userId) {
        
        try {

            boolean existReview = travelReviewRepository.existsByReviewNumber(reviewNumber);
            if(!existReview) return ResponseDto.noExistBoard();
            
            TravelCommentEntity travelCommentEntity = travelCommentRepository.findByCommentNumber(commentNumber);
            if(travelCommentEntity == null) return ResponseDto.noExistComment();

            String writerId = travelCommentEntity.getCommentWriterId();
            boolean iswriterId = userId.equals(writerId);
            if(!iswriterId) return ResponseDto.authorizationFailed();
            
            
            travelCommentRepository.delete(travelCommentEntity);

        } catch(Exception exception) {
            return ResponseDto.databaseError();
        }

        return ResponseDto.success();
        
    }

}        
