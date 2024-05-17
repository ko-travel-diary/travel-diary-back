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
    
    private String tourattractionsImageUrl;
    private String tourattractionsName;
    private String tourattractionsLocation;
    private String tourattractionsTelNumber;
    private String tourattractionsHours;

    private TourAttractionsListItem (GetTourAttractionsResultSet resultSet) {
        this.tourattractionsImageUrl = resultSet.getImage();
        this.tourattractionsName = resultSet.getTourAttractionsName();
        this.tourattractionsLocation = resultSet.getTourAttractionsLocation();
        this.tourattractionsTelNumber = resultSet.getTourAttractionsTelNumber();
        this.tourattractionsHours = resultSet.getTourAttractionsHours();
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
