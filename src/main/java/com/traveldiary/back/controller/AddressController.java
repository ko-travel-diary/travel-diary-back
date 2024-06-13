package com.traveldiary.back.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.traveldiary.back.dto.response.address.GetSearchAddressResponseDto;
import com.traveldiary.back.dto.response.address.GetSearchCoordinateResponseDto;
import com.traveldiary.back.service.AddressService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/traveldiary/v1/address")
@RequiredArgsConstructor
public class AddressController {

    private final AddressService addressService;
    
    @GetMapping("/search")
    public ResponseEntity<? super GetSearchCoordinateResponseDto> getLatLng(
        @RequestParam("query") String query
    ) {
        ResponseEntity<? super GetSearchCoordinateResponseDto> response = addressService.SearchCoordinate(query);
        return response;
    };

    @GetMapping("/query")
    public ResponseEntity<? super GetSearchAddressResponseDto> getAddress(
        @RequestParam("query") String query, Integer size, Integer page
    ) {
        ResponseEntity<? super GetSearchAddressResponseDto> response = addressService.SearchAddress(query, size, page);
        return response;
    }

}
