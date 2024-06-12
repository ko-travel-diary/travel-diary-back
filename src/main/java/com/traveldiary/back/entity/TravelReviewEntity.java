package com.traveldiary.back.entity;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;

import com.traveldiary.back.common.util.ChangeDateFormatUtil;
import com.traveldiary.back.dto.request.review.PatchTravelReviewRequestDto;
import com.traveldiary.back.dto.request.review.PostTravelReviewRequestDto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "travelReview")
@Table(name = "travel_review")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TravelReviewEntity {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer reviewNumber;

    private String reviewTitle;
    private String reviewContent;
    private String reviewDatetime;
    private String reviewWriterId;
    private Integer reviewFavoriteCount;
    private Integer reviewViewCount;
    private Integer travelScheduleNumber;

    public TravelReviewEntity(PostTravelReviewRequestDto dto, String userId) throws Exception {

        String dateTime = ChangeDateFormatUtil.nowDate();

        this.reviewTitle = dto.getReviewTitle();
        this.reviewContent = dto.getReviewContent();
        this.reviewDatetime = dateTime;
        this.reviewWriterId = userId;
        this.reviewFavoriteCount = 0;
        this.reviewViewCount = 0;
        this.travelScheduleNumber = dto.getTravelScheduleNumber();
    }

    public void update(PatchTravelReviewRequestDto dto) {
        this.reviewTitle = dto.getReviewTitle();
        this.reviewContent = dto.getReviewContent();
        this.travelScheduleNumber = dto.getTravelScheduleNumber();
    }

    public void increaseFavoriteCount() {
        this.reviewFavoriteCount++;
    }

    public void decreaseFavoriteCount() {
        this.reviewFavoriteCount--;
    }

    public void increaseViewCount() {
        this.reviewViewCount++;
    }

}