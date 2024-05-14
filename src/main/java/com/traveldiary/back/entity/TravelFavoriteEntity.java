package com.traveldiary.back.entity;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;

import com.traveldiary.back.dto.request.review.PatchTravelFavoriteReviewRequestDto;
import com.traveldiary.back.dto.request.review.PostTravelReviewRequestDto;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "travelFavorite")
@Table(name = "travel_favorite")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TravelFavoriteEntity {
    @Id
    private String userId;
    private Integer reviewNumber;

    public TravelFavoriteEntity(PatchTravelFavoriteReviewRequestDto dto, String userId) {

        this.userId = dto.getUserId();
        this.reviewNumber = dto.getReviewNumber();
    }
}


