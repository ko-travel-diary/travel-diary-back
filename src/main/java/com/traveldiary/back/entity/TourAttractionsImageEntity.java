package com.traveldiary.back.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
    private Integer tourAttractionsImageNumber;
    private Integer tourAttractionsNumber;
    private String tourAttractionsImageUrl;
}
