package com.traveldiary.back.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "TravelReviewViewEntity")
@Table(name = "travel_review_view")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TravelReviewViewEntity {

    @Id
    private Integer reviewNumber;

    private String image;
    private String reviewTitle;
    private String reviewContent;
    private String reviewWriterId;
    private String reviewDatetime;
    private Integer reviewFavoriteCount;
    private Integer reviewViewCount;

}