package com.traveldiary.back.common.object;

import java.util.List;

import com.traveldiary.back.entity.QnaEntity;

import lombok.Getter;

@Getter
public class QnaBoardListItem {
    private Integer receptionNumber;
    private String qnaTitle;
    private String qnaContent;
    private String qnaWriterId;
    private String reviewDatetime;
    private Integer qnaViewCount;

    private QnaBoardListItem (QnaEntity qnaEntity) {

        this.receptionNumber = qnaEntity.getReceptionNumber();
        this.qnaTitle = qnaEntity.getQnaTitle();
        this.qnaContent = qnaEntity.getQnaContent();
        this.qnaWriterId = qnaEntity.getQnaWriterId();
        this.reviewDatetime = qnaEntity.getQnaDatetime();
    }
}
