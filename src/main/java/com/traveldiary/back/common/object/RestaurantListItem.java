package com.traveldiary.back.common.object;

import java.util.ArrayList;
import java.util.List;

import com.traveldiary.back.entity.RestaurantViewEntity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RestaurantListItem {

    private Integer restaurantNumber;
    private String restaurantImageUrl;
    private String restaurantName;
    private String restaurantLocation;
    private String restaurantTelNumber;
    private String restaurantOutline;
    private String restaurantHours;
    private String restaurantMainMenu;
    private String restaurantServiceMenu;
    private Double restaurantLat;
    private Double restaurantLng;
    private Integer restaurantRecommendCount;

    private RestaurantListItem(RestaurantViewEntity resultSet) {
        this.restaurantNumber = resultSet.getRestaurantNumber();
        this.restaurantImageUrl = resultSet.getImage();
        this.restaurantName = resultSet.getRestaurantName();
        this.restaurantLocation = resultSet.getRestaurantLocation();
        this.restaurantTelNumber = resultSet.getRestaurantTelNumber();
        this.restaurantOutline = resultSet.getRestaurantOutline();
        this.restaurantHours = resultSet.getRestaurantHours();
        this.restaurantMainMenu = resultSet.getRestaurantMainMenu();
        this.restaurantServiceMenu = resultSet.getRestaurantServiceMenu();
        this.restaurantLat = resultSet.getRestaurantLat() != null ? resultSet.getRestaurantLat() : 0.0;
        this.restaurantLng = resultSet.getRestaurantLng() != null ? resultSet.getRestaurantLng() : 0.0;
        this.restaurantRecommendCount = resultSet.getRestaurantRecommendCount();
    }

    public static List<RestaurantListItem> getRestaurantList(List<RestaurantViewEntity> resultSets) {

        List<RestaurantListItem> restaurantList = new ArrayList<>();

        for(RestaurantViewEntity resultSet : resultSets){

            RestaurantListItem tRestaurantListItem = new RestaurantListItem(resultSet);
            restaurantList.add(tRestaurantListItem);

        }

        return restaurantList;

    }

}
