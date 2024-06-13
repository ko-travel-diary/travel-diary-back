package com.traveldiary.back.service.implementation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.traveldiary.back.common.util.GetRequestUtil;
import com.traveldiary.back.dto.response.address.GetSearchAddressResponseDto;
import com.traveldiary.back.dto.response.address.GetSearchCoordinateResponseDto;
import com.traveldiary.back.service.AddressService;

@Service
public class AddressServiceImplementation implements AddressService {

    @Value("${kakao.rest-api-key}") private String restKey;
    @Value("${kakao.coordinate-url}") private String coordinateUrl;
    @Value("${kakao.address-url}") private String addressUrl;

    GetSearchCoordinateResponseDto getSearchCoordinateResponseDto = null;

    Double x = null;
    Double y = null;

    @Override
    public ResponseEntity<? super GetSearchCoordinateResponseDto> SearchCoordinate(String query){

        try {

            JsonNode result = GetRequestUtil.coordinateRequest(query, restKey, coordinateUrl);
            JsonNode documents = result.path("documents");
            
            JsonNode data = documents.get(0);
            x = data.path("x").asDouble();
            y = data.path("y").asDouble();

        } catch(Exception exception) {
            exception.printStackTrace();
            return null;                                                                                                                                         
        }

        return GetSearchCoordinateResponseDto.success(x, y);

    }


    @Override
    public ResponseEntity<? super GetSearchAddressResponseDto> SearchAddress(String query, Integer size, Integer page) {

        List<String> addresses = new ArrayList<>();

        try {

            JsonNode result = GetRequestUtil.addressRequest(query, restKey, addressUrl, size, page);

            for(JsonNode document: result.path("documents")) {
                String address = document.path("address_name").asText();
                addresses.add(address);
            }

        } catch(Exception exception) {
            exception.printStackTrace();
            return null;
        }

        return GetSearchAddressResponseDto.success(addresses);
        
    }

} 

