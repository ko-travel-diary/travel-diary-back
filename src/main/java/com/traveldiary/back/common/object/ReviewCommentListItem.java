package com.traveldiary.back.common.object;

import java.util.ArrayList;
import java.util.List;

import com.traveldiary.back.entity.TravelCommentEntity;

import lombok.Getter;

@Getter
public class ReviewCommentListItem {
    
    private Integer reviewCommentNumber;
    private String reviewCommentWriterId;
    private String commentContent;

    public ReviewCommentListItem(TravelCommentEntity travelCommentEntity) {
        this.reviewCommentNumber = travelCommentEntity.getCommentNumber();
        this.reviewCommentWriterId = travelCommentEntity.getCommentWriterId();
        this.commentContent = travelCommentEntity.getCommentContent();
    }

    public static List<ReviewCommentListItem> getCommentList(List<TravelCommentEntity> travelCommentEntities) {
        List<ReviewCommentListItem> reviewCommentList = new ArrayList<>();

        for(TravelCommentEntity travelCommentEntity: travelCommentEntities) {
            ReviewCommentListItem reviewCommentListItem = new ReviewCommentListItem(travelCommentEntity);
            reviewCommentList.add(reviewCommentListItem);
        }

        return reviewCommentList;
    }
}
