package com.traveldiary.back.service.implementation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.traveldiary.back.dto.request.restaurant.PatchRestaurantRequestDto;
import com.traveldiary.back.dto.request.restaurant.PostRestaurantRequestDto;
import com.traveldiary.back.dto.response.ResponseDto;
import com.traveldiary.back.dto.response.restaurant.GetRestaurantListResponseDto;
import com.traveldiary.back.dto.response.restaurant.GetRestaurantResponseDto;
import com.traveldiary.back.dto.response.restaurant.GetSearchRestaurantListResponseDto;
import com.traveldiary.back.entity.RestaurantEntity;
import com.traveldiary.back.entity.RestaurantImageEntity;
import com.traveldiary.back.entity.RestaurantViewEntity;
import com.traveldiary.back.repository.RestaurantImageRepository;
import com.traveldiary.back.repository.RestaurantRepository;
import com.traveldiary.back.repository.RestaurantViewRepository;
import com.traveldiary.back.service.RestaurantService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RestaurantServiceImplementation implements RestaurantService{

    private final RestaurantRepository restaurantRepository;
    private final RestaurantImageRepository restaurantImageRepository;
    private final RestaurantViewRepository restaurantViewRepository;

    @Override
    public ResponseEntity<ResponseDto> postRestaurant(PostRestaurantRequestDto dto, String userId) {

        try{

            String restaurantName = dto.getRestaurantName();
            boolean existsed = restaurantRepository.existsByRestaurantName(restaurantName);
            if(existsed) return ResponseDto.duplicatedRestaurant();

            RestaurantEntity restaurantEntity = new RestaurantEntity(dto);
            restaurantRepository.save(restaurantEntity);

            int restaurantNumber = restaurantEntity.getRestaurantNumber();

            List<String> images = dto.getRestaurantImageUrl();
            for(String image : images){
                RestaurantImageEntity imageEntity = new RestaurantImageEntity(restaurantNumber, image);
                restaurantImageRepository.save(imageEntity);
            }
            if(images.isEmpty() || images.get(0) == null) {
                String image = "https://cdn-icons-png.flaticon.com/128/11423/11423562.png";
                RestaurantImageEntity imageEntity = new RestaurantImageEntity(restaurantNumber, image);
                restaurantImageRepository.save(imageEntity);
            }
            
        }catch (Exception exception){
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return ResponseDto.success();

    }

    @Override
    public ResponseEntity<? super GetRestaurantListResponseDto> getRestaurantList(Double lat, Double lng) {

        List<RestaurantViewEntity> resultSets = new ArrayList<>();

        try {
            
            if (lat == null || lng == null)
                resultSets = restaurantViewRepository.findBy();
            else
                resultSets = restaurantViewRepository.getRestaurantRangeList(lat, lng);
            
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

    return GetRestaurantListResponseDto.success(resultSets);

    }

    @Override
    public ResponseEntity<? super GetSearchRestaurantListResponseDto> getSearchRestaurantList(String searchWord) {

        List<RestaurantViewEntity> resultSets = new ArrayList<>();

        try {
            resultSets = restaurantViewRepository.findByRestaurantNameContains(searchWord);
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

    return GetSearchRestaurantListResponseDto.success(resultSets);

    }

    @Override
    public ResponseEntity<? super GetRestaurantResponseDto> getRestaurant(Integer restaurantNumber) {

        RestaurantEntity restaurantEntity = null;
        List<String> restaurantImageUrl = new ArrayList<>();

        try{

            restaurantEntity = restaurantRepository.findByRestaurantNumber(restaurantNumber);
            if(restaurantEntity == null) return ResponseDto.noExistData();

            restaurantImageUrl = new ArrayList<>();
            List<RestaurantImageEntity> restaurantImageEntities = restaurantImageRepository.findByRestaurantNumber(restaurantNumber);
            for(RestaurantImageEntity entity: restaurantImageEntities){
                String image = entity.getRestaurantImageUrl();
                restaurantImageUrl.add(image);
            }

        }catch(Exception exception){
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return GetRestaurantResponseDto.success(restaurantEntity, restaurantImageUrl);

    }

    @Override
    public ResponseEntity<ResponseDto> patchRestaurant(PatchRestaurantRequestDto dto, Integer restaurantNumber, String userId) {
        
        try {

            List<RestaurantImageEntity> restaurantImageEntities = restaurantImageRepository.findByRestaurantNumber(restaurantNumber);
            restaurantImageRepository.deleteAll(restaurantImageEntities);
            
            RestaurantEntity restaurantEntity = restaurantRepository.findByRestaurantNumber(restaurantNumber);
            if (restaurantEntity == null) return ResponseDto.noExistData();

            restaurantEntity.update(dto);
            restaurantRepository.save(restaurantEntity);
            
            List<String> images = dto.getRestaurantImageUrl();
            for (String image : images) {
                RestaurantImageEntity restaurantImageEntity = new RestaurantImageEntity(restaurantNumber, image);
                restaurantImageRepository.save(restaurantImageEntity);
            }
            
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return ResponseDto.success();

    }

    @Override
    public ResponseEntity<ResponseDto> deleteRestaurant(Integer restaurantNumber, String userId) {

        try {

            List<RestaurantImageEntity> restaurantImageEntities = restaurantImageRepository.findByRestaurantNumber(restaurantNumber);
            restaurantImageRepository.deleteAll(restaurantImageEntities);

            RestaurantEntity restaurantEntity = restaurantRepository.findByRestaurantNumber(restaurantNumber);
            restaurantRepository.delete(restaurantEntity);
            
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return ResponseDto.success();
        
    }

}