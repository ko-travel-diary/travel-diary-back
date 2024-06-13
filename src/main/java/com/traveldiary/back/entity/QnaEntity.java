package com.traveldiary.back.entity;

import com.traveldiary.back.common.util.ChangeDateFormatUtil;
import com.traveldiary.back.dto.request.qna.PatchQnaCommentRequestDto;
import com.traveldiary.back.dto.request.qna.PatchQnaRequestDto;
import com.traveldiary.back.dto.request.qna.PostQnaCommentRequestDto;
import com.traveldiary.back.dto.request.qna.PostQnaRequestDto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "qna")
@Table(name = "qna")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class QnaEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer receptionNumber;

    private String qnaTitle;
    private String qnaContent;
    private String qnaDatetime;
    private Boolean qnaStatus;
    private String qnaWriterId;
    private String qnaComment;

    public QnaEntity(PostQnaRequestDto dto, String userId) throws Exception {

        String dateTime = ChangeDateFormatUtil.nowDate();

        this.qnaTitle = dto.getQnaTitle();
        this.qnaContent = dto.getQnaContent();
        this.qnaDatetime = dateTime;
        this.qnaStatus = false;
        this.qnaWriterId = userId;
    }

    public void update(PatchQnaRequestDto dto) {
        this.qnaTitle = dto.getQnaTitle();
        this.qnaContent = dto.getQnaContent();
    }

    public void updateComment(PatchQnaCommentRequestDto dto) {
        this.qnaComment = dto.getQnaComment();
    }

    public void postComment(PostQnaCommentRequestDto dto) {
        this.qnaStatus = true;
        this.qnaComment = dto.getQnaComment();
    }

}