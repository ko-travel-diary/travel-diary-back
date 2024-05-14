package com.traveldiary.back.dto.response.qna;

import java.util.ArrayList;
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
public class GetQnaBoardResponseDto extends ResponseDto{

    private List<QnaListItem> qnaList;

    private GetQnaBoardResponseDto(List<QnaEntity> qnaEntities) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.qnaList = QnaListItem.getQnaList(qnaEntities);
    }
    
    public static ResponseEntity<GetQnaBoardResponseDto> success (List<QnaEntity> qnaEntities) {
        GetQnaBoardResponseDto reponseBody = new GetQnaBoardResponseDto(qnaEntities);
        return ResponseEntity.status(HttpStatus.OK).body(reponseBody);
    }
}
