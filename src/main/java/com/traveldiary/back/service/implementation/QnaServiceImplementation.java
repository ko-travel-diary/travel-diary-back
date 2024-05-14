package com.traveldiary.back.service.implementation;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

import com.traveldiary.back.dto.request.qna.GetQnaRequestDto;
import com.traveldiary.back.dto.request.qna.PostQnaRequestDto;
import com.traveldiary.back.dto.response.ResponseDto;
import com.traveldiary.back.dto.response.qna.GetQnaBoardResponseDto;
import com.traveldiary.back.dto.response.qna.GetQnaResponseDto;
import com.traveldiary.back.dto.response.qna.GetSearchQnaBoardResponseDto;
import com.traveldiary.back.entity.QnaEntity;
import com.traveldiary.back.repository.QnaRepository;
import com.traveldiary.back.repository.UserRepository;
import com.traveldiary.back.service.QnaService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
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
}
