package com.traveldiary.back.service;

import org.springframework.http.ResponseEntity;

import com.traveldiary.back.dto.request.qna.PatchQnaCommentRequestDto;
import com.traveldiary.back.dto.request.qna.PatchQnaRequestDto;
import com.traveldiary.back.dto.request.qna.PostQnaCommentRequestDto;
import com.traveldiary.back.dto.request.qna.PostQnaRequestDto;
import com.traveldiary.back.dto.response.ResponseDto;
import com.traveldiary.back.dto.response.qna.GetQnaBoardResponseDto;
import com.traveldiary.back.dto.response.qna.GetQnaResponseDto;
import com.traveldiary.back.dto.response.qna.GetSearchQnaBoardResponseDto;

public interface QnaService {
    
    ResponseEntity<ResponseDto> postQna(PostQnaRequestDto dto, String userId);
    ResponseEntity<ResponseDto> postQnaComment(PostQnaCommentRequestDto dto, Integer receptionNumber);

    ResponseEntity<? super GetQnaBoardResponseDto> getQnaList();
    ResponseEntity<? super GetSearchQnaBoardResponseDto> getSearchQnaList(String searchWord);
    ResponseEntity<? super GetQnaResponseDto> getQnaBoard(Integer receptionNumber);

    ResponseEntity<ResponseDto> patchQna(PatchQnaRequestDto dto, Integer receptionNumber, String userId);
    ResponseEntity<ResponseDto> patchQnaComment(PatchQnaCommentRequestDto dto, Integer receptionNumber);
    
    ResponseEntity<ResponseDto> deleteQna(Integer receptionNumber, String userId);
    ResponseEntity<ResponseDto> deleteQnaComment(Integer receptionNumber, String userId);
    
}
