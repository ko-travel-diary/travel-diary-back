package com.traveldiary.back.controller;

import java.io.UnsupportedEncodingException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.traveldiary.back.service.AddressService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/traveldiary/v1/address")
@RequiredArgsConstructor
public class AddressController {

    private final AddressService addressService;
    
    @GetMapping("/search")
    public JsonNode  getLatLng (
        @RequestParam("query") String query
    ) throws UnsupportedEncodingException {
        JsonNode response = addressService.SearchAddress(query);
        return response;
    };

}
