package com.traveldiary.back.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "travel_favorite")
@Table(name = "travel_favorite")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TravelFavoriteEntity {
    private String userId;
    private Integer reviewNumber;
}
