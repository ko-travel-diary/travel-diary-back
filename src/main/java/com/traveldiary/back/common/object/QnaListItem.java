package com.traveldiary.back.common.object;

import java.util.ArrayList;
import java.util.List;

import com.traveldiary.back.entity.QnaEntity;

import lombok.Getter;

@Getter
public class QnaListItem {

    private Integer receptionNumber;
    private String qnaTitle;
    private String qnaContent;
    private String qnaWriterId;
    private String qnaDatetime;
    private Boolean qnaStatus;
    
    public QnaListItem (QnaEntity entity) {

        this.receptionNumber = entity.getReceptionNumber();
        this.qnaTitle = entity.getQnaTitle();
        this.qnaContent = entity.getQnaContent();
        this.qnaWriterId = entity.getQnaWriterId();
        this.qnaDatetime = entity.getQnaDatetime();
        this.qnaStatus = entity.getQnaStatus();

    }

    public static List<QnaListItem> getQnaList (List<QnaEntity> entities) {
        List<QnaListItem> qnaList = new ArrayList<>();

        for (QnaEntity qnaEntity: entities) {
            QnaListItem qnaListItem  = new QnaListItem(qnaEntity);
            qnaList.add(qnaListItem);
        }

        return qnaList;
    }
}
