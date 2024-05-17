package com.traveldiary.back.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "tourAttractionsImage")
@Table(name = "tour_attractions_image")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TourAttractionsImageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer tourAttractionsImageNumber;
    @NotNull
    private Integer tourAttractionsNumber;
    @NotNull
    private String tourAttractionsImageUrl;
    
    public TourAttractionsImageEntity (Integer tourAttractionsNumber, String tourAttractionsImageUrl){
        this.tourAttractionsNumber = tourAttractionsNumber;
        this.tourAttractionsImageUrl = tourAttractionsImageUrl;
    }
}
