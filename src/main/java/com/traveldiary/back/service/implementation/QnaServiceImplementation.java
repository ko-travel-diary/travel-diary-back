package com.traveldiary.back.service.implementation;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

import com.traveldiary.back.dto.request.qna.PatchQnaCommentRequestDto;
import com.traveldiary.back.dto.request.qna.PatchQnaRequestDto;
import com.traveldiary.back.dto.request.qna.PostQnaCommentRequestDto;
import com.traveldiary.back.dto.request.qna.PostQnaRequestDto;
import com.traveldiary.back.dto.response.ResponseDto;
import com.traveldiary.back.dto.response.qna.GetQnaBoardResponseDto;
import com.traveldiary.back.dto.response.qna.GetQnaResponseDto;
import com.traveldiary.back.dto.response.qna.GetSearchQnaBoardResponseDto;
import com.traveldiary.back.entity.QnaEntity;
import com.traveldiary.back.entity.UserEntity;
import com.traveldiary.back.repository.QnaRepository;
import com.traveldiary.back.repository.UserRepository;
import com.traveldiary.back.service.QnaService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class QnaServiceImplementation implements QnaService{

    private final UserRepository userRepository;
    private final QnaRepository qnaRepository;

    @Override
    public ResponseEntity<ResponseDto> postQna(PostQnaRequestDto dto, String userId) {

        UserEntity userEntity;
        QnaEntity qnaEntity;
        
        try {

            userEntity = userRepository.findByUserId(userId);
            String role = userEntity.getUserRole();
            if (role == "ROLE_ADMIN") return ResponseDto.authorizationFailed();

            boolean ifExists = userRepository.existsById(userId);
            if (!ifExists) return ResponseDto.authenticationFailed();

            qnaEntity = new QnaEntity(dto, userId);
            qnaRepository.save(qnaEntity);
            

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return ResponseDto.success();
    }

    @Override
    public ResponseEntity<? super GetQnaBoardResponseDto> getQnaList() {
        
        List<QnaEntity> qnaEntities;

        try {
            
            qnaEntities = qnaRepository.findByOrderByReceptionNumberDesc();

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

    return GetQnaBoardResponseDto.success(qnaEntities);
    }

    @Override
    public ResponseEntity<? super GetSearchQnaBoardResponseDto> getSearchQnaList(String searchWord) {

        List<QnaEntity> qnaEntities;

        try {

            qnaEntities = qnaRepository.findByQnaTitleContainsOrderByReceptionNumberDesc(searchWord);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return GetSearchQnaBoardResponseDto.success(qnaEntities);
    }

    @Override
    public ResponseEntity<? super GetQnaResponseDto> getQnaBoard(Integer receptionNumber) {

        QnaEntity qnaEntity;

        try {
            
            qnaEntity = qnaRepository.findByReceptionNumber(receptionNumber);
            if (qnaEntity == null) return ResponseDto.noExistBoard();

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return GetQnaResponseDto.success(qnaEntity);
    }

    @Override
    public ResponseEntity<ResponseDto> postQnaComment(PostQnaCommentRequestDto dto, Integer receptionNumber) {
    
        QnaEntity qnaEntity;

        try {

            qnaEntity = qnaRepository.findByReceptionNumber(receptionNumber);
            if (qnaEntity == null) return ResponseDto.noExistBoard();

            boolean status = qnaEntity.getQnaStatus();
            if (status) return ResponseDto.writtenComment();

            String comment = dto.getQnaComment();
            qnaEntity.setQnaStatus(true);
            qnaEntity.setQnaComment(comment);

            qnaRepository.save(qnaEntity);
            
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return ResponseDto.success();
    }

    @Override
    public ResponseEntity<ResponseDto> patchQna(PatchQnaRequestDto dto, Integer receptionNumber, String userId) {
        
        QnaEntity qnaEntity;

        try {

            qnaEntity = qnaRepository.findByReceptionNumber(receptionNumber);
            if (qnaEntity == null) return ResponseDto.noExistBoard();

            String writerId = qnaEntity.getQnaWriterId();
            boolean isWriter = userId.equals(writerId);
            if (!isWriter) return ResponseDto.authorizationFailed();

            boolean status = qnaEntity.getQnaStatus();
            if (status) return ResponseDto.writtenComment();

            qnaEntity.update(dto);

            qnaRepository.save(qnaEntity);
    
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return ResponseDto.success();
    }

    @Override
    public ResponseEntity<ResponseDto> deleteQna(Integer receptionNumber, String userId) {
        
        QnaEntity qnaEntity;

        try {

            qnaEntity = qnaRepository.findByReceptionNumber(receptionNumber);
            if (qnaEntity == null) return ResponseDto.noExistBoard();

            String writerId = qnaEntity.getQnaWriterId();
            boolean isWriter = userId.equals(writerId);
            if(!isWriter) return ResponseDto.authenticationFailed();

            qnaRepository.delete(qnaEntity);
            
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return ResponseDto.success();
    }

    @Override
    public ResponseEntity<ResponseDto> patchQnaComment(PatchQnaCommentRequestDto dto, Integer receptionNumber) {
        
        QnaEntity qnaEntity;

        try {
            
            qnaEntity = qnaRepository.findByReceptionNumber(receptionNumber);
            if (qnaEntity == null) return ResponseDto.noExistBoard();

            boolean status = qnaEntity.getQnaStatus();
            if (!status) return ResponseDto.noExistComment();

            qnaEntity.updateComment(dto);

            qnaRepository.save(qnaEntity);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return ResponseDto.success();
    }

    @Override
    public ResponseEntity<ResponseDto> deleteQnaComment(Integer receptionNumber, String userId) {
        
        QnaEntity qnaEntity;

        try {

            qnaEntity = qnaRepository.findByReceptionNumber(receptionNumber);
            if (qnaEntity == null) return ResponseDto.noExistBoard();

            UserEntity userEntity = userRepository.findByUserId(userId);
            String role = userEntity.getUserRole();
            if (role == "ROLE_USER") return ResponseDto.authenticationFailed();

            qnaEntity.setQnaStatus(false);
            qnaEntity.setQnaComment(null);

            qnaRepository.save(qnaEntity);
            
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return ResponseDto.success();
    }
}