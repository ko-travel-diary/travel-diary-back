package com.traveldiary.back.common.object;

import java.util.ArrayList;
import java.util.List;

import com.traveldiary.back.repository.resultSet.GetTourAttractionsResultSet;

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

    private TourAttractionsListItem (GetTourAttractionsResultSet resultSet) {
        this.tourAttractionsNumber = resultSet.getTourAttractionsNumber();
        this.tourAttractionsImageUrl = resultSet.getImage();
        this.tourAttractionsName = resultSet.getTourAttractionsName();
        this.tourAttractionsLocation = resultSet.getTourAttractionsLocation();
        this.tourAttractionsTelNumber = resultSet.getTourAttractionsTelNumber();
        this.tourAttractionsHours = resultSet.getTourAttractionsHours();
        this.tourAttractionsOutline = resultSet.getTourAttractionsOutline();
    }

    public static List<TourAttractionsListItem> getTourAttractionsList(List<GetTourAttractionsResultSet> resultSets) {
        List<TourAttractionsListItem> tourAttractionsList = new ArrayList<>();
        for(GetTourAttractionsResultSet resultSet : resultSets){
            TourAttractionsListItem tourAttractionsListItem = new TourAttractionsListItem(resultSet);
            tourAttractionsList.add(tourAttractionsListItem);
        }
        return tourAttractionsList;
    }

}
