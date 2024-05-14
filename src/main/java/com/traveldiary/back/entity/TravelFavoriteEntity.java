package com.traveldiary.back.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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
    @Id
    private String userId;
    private Integer reviewNumber;
}
