package com.traveldiary.back.common.object;

import java.util.ArrayList;
import java.util.List;

import com.traveldiary.back.entity.TravelReviewViewEntity;

import lombok.Getter;

@Getter
public class ReviewBoardListItem {
    
    private Integer reviewNumber;
    private String reviewTitle;
    private String reviewContent;
    private String writerId;
    private String reviewDatetime;
    private String travelReviewImageUrl;
    private Integer reviewViewCount;
    private Integer reviewFavoriteCount;

    public ReviewBoardListItem(TravelReviewViewEntity travelReviewViewEntity) {
        this.reviewNumber = travelReviewViewEntity.getReviewNumber();
        this.reviewTitle = travelReviewViewEntity.getReviewTitle();
        this.reviewContent = travelReviewViewEntity.getReviewContent();
        this.writerId = travelReviewViewEntity.getReviewWriterId();
        this.reviewDatetime = travelReviewViewEntity.getReviewDatetime();
        this.reviewViewCount = travelReviewViewEntity.getReviewViewCount();
        this.reviewFavoriteCount = travelReviewViewEntity.getReviewFavoriteCount();
        this.travelReviewImageUrl = travelReviewViewEntity.getImage();
    }

    public static List<ReviewBoardListItem> getReviewList(List<TravelReviewViewEntity> travelReviewViewEntities) {

        List<ReviewBoardListItem> reviewBoardList = new ArrayList<>();

        for(TravelReviewViewEntity travelReviewViewEntity: travelReviewViewEntities) {
            ReviewBoardListItem reviewBoardListItem = new ReviewBoardListItem(travelReviewViewEntity);
            reviewBoardList.add(reviewBoardListItem);
        }

        return reviewBoardList;

    }

}