package com.traveldiary.back.common.object;

import java.util.ArrayList;
import java.util.List;

import com.traveldiary.back.entity.TravelReviewEntity;

import lombok.Getter;

@Getter
public class ReviewBoardListItem {
    
    private Integer reviewNumber;
    private String reviewTitle;
    private String reviewContent;
    private String writerId;
    private String reviewDatetime;
    private List<String> travelReviewImageUrl;
    private Integer reviewViewCount;
    private Integer reviewFavoriteCount;

    public ReviewBoardListItem(TravelReviewEntity travelReviewEntity) {

        String writerId = travelReviewEntity.getReviewWriterId();
        writerId = writerId.substring(0, 1) + "*".repeat(writerId.length() - 1);

        this.reviewNumber = travelReviewEntity.getReviewNumber();
        this.reviewTitle = travelReviewEntity.getReviewTitle();
        this.reviewContent = travelReviewEntity.getReviewContent();
        this.writerId = writerId;
        this.reviewDatetime = travelReviewEntity.getReviewDatetime();
        this.travelReviewImageUrl = null;
        this.reviewViewCount = travelReviewEntity.getReviewViewCount();
        this.reviewFavoriteCount = travelReviewEntity.getReviewFavoriteCount();
    }

    public static List<ReviewBoardListItem> getReviewList(List<TravelReviewEntity> travelReviewEntities) {
        List<ReviewBoardListItem> reviewBoardList = new ArrayList<>();

        for(TravelReviewEntity travelReviewEntity: travelReviewEntities) {
            ReviewBoardListItem reviewBoardListItem = new ReviewBoardListItem(travelReviewEntity);
            reviewBoardList.add(reviewBoardListItem);
        }

        return reviewBoardList;
    }
}