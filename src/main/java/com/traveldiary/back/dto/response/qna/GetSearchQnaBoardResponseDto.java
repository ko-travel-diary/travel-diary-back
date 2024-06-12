package com.traveldiary.back.dto.response.qna;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.traveldiary.back.common.object.QnaListItem;
import com.traveldiary.back.dto.response.ResponseCode;
import com.traveldiary.back.dto.response.ResponseDto;
import com.traveldiary.back.dto.response.ResponseMessage;
import com.traveldiary.back.entity.QnaEntity;

import lombok.Getter;

@Getter
public class GetSearchQnaBoardResponseDto extends ResponseDto {
    
    private List<QnaListItem> searchQnaList;

    private GetSearchQnaBoardResponseDto(List<QnaEntity> qnaEntities) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.searchQnaList = QnaListItem.getQnaList(qnaEntities);
    }

    public static ResponseEntity<GetSearchQnaBoardResponseDto> success(List<QnaEntity> qnaEntities) {
        GetSearchQnaBoardResponseDto responsebody = new GetSearchQnaBoardResponseDto(qnaEntities);
        return ResponseEntity.status(HttpStatus.OK).body(responsebody);
    }

}