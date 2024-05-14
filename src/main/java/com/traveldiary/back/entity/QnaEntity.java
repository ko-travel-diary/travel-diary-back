package com.traveldiary.back.entity;

import jakarta.persistence.Entity;
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
    private Integer receptionNumber;
    private String qnaTitle;
    private String qnaContent;
    private String qnaDatetime;
    private Boolean qnaStatus;
    private String qnaWriterId;
    private Integer qnaViewCount;
    private String qnaComment;
}
