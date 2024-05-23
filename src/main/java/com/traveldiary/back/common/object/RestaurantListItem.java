package com.traveldiary.back.common.object;

import java.util.ArrayList;
import java.util.List;

import com.traveldiary.back.repository.resultSet.GetRestaurantResultSet;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RestaurantListItem {
    private String restaurantImageUrl;
    private String restaurantName;
    private String restaurantLocation;
    private String restaurantTelNumber;
    private String restaurantHours;

    private RestaurantListItem (GetRestaurantResultSet resultSet) {
        this.restaurantImageUrl = resultSet.getImage();
        this.restaurantName = resultSet.getRestaurantName();
        this.restaurantLocation = resultSet.getRestaurantLocation();
        this.restaurantTelNumber = resultSet.getRestaurantTelNumber();
        this.restaurantHours = resultSet.getRestaurantHours();
    }

    public static List<RestaurantListItem> getRestaurantList (List<GetRestaurantResultSet> resultSets) {
        List<RestaurantListItem> restaurantList = new ArrayList<>();
        for(GetRestaurantResultSet resultSet : resultSets){
            RestaurantListItem tRestaurantListItem = new RestaurantListItem(resultSet);
            restaurantList.add(tRestaurantListItem);
        }
        return restaurantList;
    }
}
