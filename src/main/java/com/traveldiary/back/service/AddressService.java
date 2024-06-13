package com.traveldiary.back.service;

import org.springframework.http.ResponseEntity;

import com.traveldiary.back.dto.response.address.GetSearchAddressResponseDto;
import com.traveldiary.back.dto.response.address.GetSearchCoordinateResponseDto;

public interface AddressService {

    ResponseEntity<? super GetSearchCoordinateResponseDto> SearchCoordinate(String query);
    ResponseEntity<? super GetSearchAddressResponseDto> SearchAddress(String query, Integer page, Integer size);

}
