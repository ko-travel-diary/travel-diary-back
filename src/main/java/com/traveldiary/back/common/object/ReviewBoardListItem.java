package com.traveldiary.back.common.object;

import java.util.ArrayList;
import java.util.List;

import com.traveldiary.back.repository.resultSet.GetTravelReviewResultSet;

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

    public ReviewBoardListItem(GetTravelReviewResultSet resultSet) {
        this.reviewNumber = resultSet.getReviewNumber();
        this.reviewTitle = resultSet.getReviewTitle();
        this.reviewContent = resultSet.getReviewContent();
        this.writerId = resultSet.getReviewWriterId();
        this.reviewDatetime = resultSet.getReviewDatetime();
        this.reviewViewCount = resultSet.getReviewViewCount();
        this.reviewFavoriteCount = resultSet.getReviewFavoriteCount();
        this.travelReviewImageUrl = resultSet.getImage();
    }

    public static List<ReviewBoardListItem> getReviewList(List<GetTravelReviewResultSet> resultSets) {

        List<ReviewBoardListItem> reviewBoardList = new ArrayList<>();

        for(GetTravelReviewResultSet resultSet: resultSets) {
            ReviewBoardListItem reviewBoardListItem = new ReviewBoardListItem(resultSet);
            reviewBoardList.add(reviewBoardListItem);
        }

        return reviewBoardList;

    }

}