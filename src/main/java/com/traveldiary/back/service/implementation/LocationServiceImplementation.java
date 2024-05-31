package com.traveldiary.back.service.implementation;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.traveldiary.back.service.LocationService;

@Service
public class LocationServiceImplementation implements LocationService {

    @Value("${kakao.rest-api-key}") String restKey;

    @Override
    public JsonNode SearchLocation(String query) {

        String url = "http://dapi.kakao.com/v2/local/search/keyword.json?query=";
        String key = "KakaoAK " + restKey;

        URL searchUrl;
        
        try {

            searchUrl = new URL(url + query);
            
            HttpURLConnection con = (HttpURLConnection)searchUrl.openConnection();

            con.setRequestMethod("GET");
            con.setRequestProperty("Authorization", key);
            con.setRequestProperty("content-type", "application/json");

            InputStream inputStream = con.getInputStream();
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode response = objectMapper.readTree(inputStream);

            return response;
        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }
        
    }
    
}
