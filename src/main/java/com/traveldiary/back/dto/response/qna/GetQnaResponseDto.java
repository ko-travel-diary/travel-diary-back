package com.traveldiary.back.dto.response.qna;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.traveldiary.back.dto.response.ResponseCode;
import com.traveldiary.back.dto.response.ResponseDto;
import com.traveldiary.back.dto.response.ResponseMessage;
import com.traveldiary.back.entity.QnaEntity;

import lombok.Getter;

@Getter
public class GetQnaResponseDto extends ResponseDto{

    private Integer receptionNumber;
    private Boolean qnaStatus;
    private String qnaTitle;
    private String qnaContent;
    private String qnaWriterId;
    private String qnaDatetime;
    private String qnaComment;

    private GetQnaResponseDto(QnaEntity qnaEntity) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.receptionNumber = qnaEntity.getReceptionNumber();
        this.qnaStatus = qnaEntity.getQnaStatus();
        this.qnaTitle = qnaEntity.getQnaTitle();
        this.qnaContent = qnaEntity.getQnaContent();
        this.qnaWriterId = qnaEntity.getQnaWriterId();
        this.qnaDatetime = qnaEntity.getQnaDatetime();
        this.qnaComment = qnaEntity.getQnaComment();
    }

    public static ResponseEntity<GetQnaResponseDto> success (QnaEntity qnaEntity) {
        GetQnaResponseDto responseBody = new GetQnaResponseDto(qnaEntity);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }
}
