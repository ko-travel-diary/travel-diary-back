package com.traveldiary.back.entity;


import com.traveldiary.back.entity.pk.FavoritePk;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
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
@IdClass(FavoritePk.class)
public class TravelFavoriteEntity {

    @Id
    private String userId;

    @Id
    private Integer reviewNumber;
    
}