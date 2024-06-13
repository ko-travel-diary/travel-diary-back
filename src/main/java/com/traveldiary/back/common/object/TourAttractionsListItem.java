package com.traveldiary.back.common.object;

import java.util.ArrayList;
import java.util.List;

import com.traveldiary.back.entity.TourAttractionViewEntity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TourAttractionsListItem {
    
    private Integer tourAttractionsNumber;
    private String tourAttractionsImageUrl;
    private String tourAttractionsName;
    private String tourAttractionsLocation;
    private String tourAttractionsTelNumber;
    private String tourAttractionsHours;
    private String tourAttractionsOutline;
    private Double tourAttractionsLat;
    private Double tourAttractionsLng;
    private Integer tourAttractionsRecommendCount;    

    private TourAttractionsListItem(TourAttractionViewEntity resultSet) {
        this.tourAttractionsNumber = resultSet.getTourAttractionsNumber();
        this.tourAttractionsImageUrl = resultSet.getImage();
        this.tourAttractionsName = resultSet.getTourAttractionsName();
        this.tourAttractionsLocation = resultSet.getTourAttractionsLocation();
        this.tourAttractionsTelNumber = resultSet.getTourAttractionsTelNumber();
        this.tourAttractionsHours = resultSet.getTourAttractionsHours();
        this.tourAttractionsOutline = resultSet.getTourAttractionsOutline();
        this.tourAttractionsLat = resultSet.getTourAttractionsLat() != null ? resultSet.getTourAttractionsLat() : 0.0;
        this.tourAttractionsLng = resultSet.getTourAttractionsLng() != null ? resultSet.getTourAttractionsLng() : 0.0;
        this.tourAttractionsRecommendCount = resultSet.getTourAttractionsRecommendCount();
    }

    public static List<TourAttractionsListItem> getTourAttractionsList(List<TourAttractionViewEntity> resultSets) {

        List<TourAttractionsListItem> tourAttractionsList = new ArrayList<>();

        for(TourAttractionViewEntity resultSet : resultSets){
            TourAttractionsListItem tourAttractionsListItem = new TourAttractionsListItem(resultSet);
            tourAttractionsList.add(tourAttractionsListItem);
        }

        return tourAttractionsList;

    }

}
