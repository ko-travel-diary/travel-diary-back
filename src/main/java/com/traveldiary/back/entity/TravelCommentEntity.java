package com.traveldiary.back.entity;

import com.traveldiary.back.dto.request.review.PatchTravelCommentRequestDto;
import com.traveldiary.back.dto.request.review.PostTravelCommentRequestDto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "comment")
@Table(name = "comment")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TravelCommentEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer commentNumber;
    private String commentContent;
    private String commentWriterId;
    private Integer commentReviewNumber;
    private Integer commentParentsNumber;

    public TravelCommentEntity(PostTravelCommentRequestDto dto, int reviewNumber, String userId) {
        this.commentContent = dto.getCommentContent();
        this.commentParentsNumber = dto.getCommentParentsNumber();
        this.commentReviewNumber = reviewNumber;
        this.commentWriterId = userId;
    }

    public TravelCommentEntity(PatchTravelCommentRequestDto dto, int commentNumber, int reviewNumber, String userId) {
        this.commentContent = dto.getCommentContent();
        this.commentNumber = commentNumber;
        this.commentReviewNumber = reviewNumber;
        this.commentWriterId = userId;
    }

    public void update(PatchTravelCommentRequestDto dto) {
        this.commentContent = dto.getCommentContent();
    }
}
