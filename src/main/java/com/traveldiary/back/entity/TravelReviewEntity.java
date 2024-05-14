package com.traveldiary.back.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "travel_review")
@Table(name = "travel_review")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TravelReviewEntity {
    private Integer reviewNumber;
    private String reviewTitle;
    private String reviewContent;
    private String reviewDatetime;
    private String reviewWriterId;
    private Integer reviewFavoriteCount;
    private Integer reviewViewCount;

}
