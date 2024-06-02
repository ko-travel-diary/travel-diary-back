package com.traveldiary.back.controller;

import java.io.UnsupportedEncodingException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.traveldiary.back.service.LocationService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/traveldiary/v1/location")
@RequiredArgsConstructor
public class LocationController {
    
    private final LocationService locationService;

    @GetMapping("search")
    public JsonNode searchLocation (
        @RequestParam("query") String query
    ) throws UnsupportedEncodingException {
        JsonNode response = locationService.SearchLocation(query);
        return response;
    }
    
}
