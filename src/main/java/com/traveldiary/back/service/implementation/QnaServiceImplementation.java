package com.traveldiary.back.service.implementation;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

import com.traveldiary.back.dto.request.qna.PatchQnaRequestDto;
import com.traveldiary.back.dto.request.qna.PostQnaCommentRequestDto;
import com.traveldiary.back.dto.request.qna.PostQnaRequestDto;
import com.traveldiary.back.dto.response.ResponseDto;
import com.traveldiary.back.dto.response.qna.GetQnaBoardResponseDto;
import com.traveldiary.back.dto.response.qna.GetQnaResponseDto;
import com.traveldiary.back.dto.response.qna.GetSearchQnaBoardResponseDto;
import com.traveldiary.back.entity.QnaEntity;
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
        
        try {

            boolean ifExists = userRepository.existsById(userId);
            if (!ifExists) {
                return ResponseDto.authenticationFailed();
            }

            QnaEntity qnaEntity = new QnaEntity(dto, userId);
            qnaRepository.save(qnaEntity);
            

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return ResponseDto.success();
    }

    @Override
    public ResponseEntity<? super GetQnaBoardResponseDto> getQnaList() {
        
        try {
            
            List<QnaEntity> qnaEntities = qnaRepository.findByOrderByReceptionNumberDesc();

            return GetQnaBoardResponseDto.success(qnaEntities);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

    }

    @Override
    public ResponseEntity<? super GetSearchQnaBoardResponseDto> getSearchQnaList(String searchWord) {

        try {

            List<QnaEntity> qnaEntities = qnaRepository.findByQnaTitleContainsOrderByReceptionNumberDesc(searchWord);

            return GetSearchQnaBoardResponseDto.success(qnaEntities);
            
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

    }

    @Override
    public ResponseEntity<? super GetQnaResponseDto> getQnaBoard(Integer receptionNumber) {

        try {
            
            QnaEntity qnaEntity = qnaRepository.findByReceptionNumber(receptionNumber);
            if (qnaEntity == null) return ResponseDto.noExistBoard();

            return GetQnaResponseDto.success(qnaEntity);
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
    }

    @Override
    public ResponseEntity<ResponseDto> postQnaComment(PostQnaCommentRequestDto dto, Integer receptionNumber) {
    
        try {

            QnaEntity qnaEntity = qnaRepository.findByReceptionNumber(receptionNumber);
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
        
        try {

            QnaEntity qnaEntity = qnaRepository.findByReceptionNumber(receptionNumber);
            if (qnaEntity == null) return ResponseDto.noExistBoard();

            String writerId = qnaEntity.getQnaWriterId();
            boolean isWriter = userId.equals(writerId);
            if (!isWriter) return ResponseDto.authenticationFailed();

            boolean status = qnaEntity.getQnaStatus();
            if (status) return ResponseDto.writtenComment();

            String qnaTitle = dto.getQnaTitle();
            String qnaContent = dto.getQnaContent();

            qnaEntity.setQnaTitle(qnaTitle);
            qnaEntity.setQnaComment(qnaContent);

            qnaRepository.save(qnaEntity);

            
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return ResponseDto.success();
    }

    @Override
    public ResponseEntity<ResponseDto> deleteQna(Integer receptionNumber, String userId) {
        
        try {

            QnaEntity qnaEntity = qnaRepository.findByReceptionNumber(receptionNumber);
            if (qnaEntity == null) return ResponseDto.noExistBoard();

            String writerId = qnaEntity.getQnaWriterId();
            boolean isWriter = userId.equals(writerId);
            if(!isWriter) return ResponseDto.authenticationFailed();

            boolean status = qnaEntity.getQnaStatus();
            if(status) return ResponseDto.writtenComment();

            qnaRepository.delete(qnaEntity);
            
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return ResponseDto.success();
    }
}
