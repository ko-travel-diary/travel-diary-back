package com.traveldiary.back.repository.resultSet;

public interface GetTravelReviewResultSet {
    Integer getReviewNumber();
    String getReviewTitle();
    String getReviewContent();
    String getReviewWriterId();
    String getReviewDatetime();
    Integer getReviewViewCount();
    Integer getReviewFavoriteCount();
    String getImage();
}
