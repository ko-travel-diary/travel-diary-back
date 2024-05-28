package com.traveldiary.back.repository.resultSet;

public interface GetRestaurantResultSet {
    Integer getRestaurantNumber();
    String getImage();
    String getRestaurantName();
    String getRestaurantLocation();
    String getRestaurantTelNumber();
    String getRestaurantOutline();
    String getRestaurantHours();
    String getRestaurantMainMenu();
    String getRestaurantServiceMenu();
    Double getRestaurantLat();
    Double getRestaurantLng();
}
