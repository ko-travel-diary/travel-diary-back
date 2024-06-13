package com.traveldiary.back.common.util;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class GetRequestUtil {
    
    public static JsonNode coordinateRequest(String query, String restKey, String url) throws IOException {
        
        String encodedAddress = EncodeDataUtil.getCoordinate(query);
        String key = "KakaoAK " + restKey;

        URL searchUrl = new URL(url + encodedAddress);

        HttpURLConnection connect = (HttpURLConnection)searchUrl.openConnection();
        connect.setRequestMethod("GET");
        connect.setRequestProperty("Authorization", key);
        connect.setRequestProperty("content-type", "application/json");

        InputStream inputStream = connect.getInputStream();
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode response = objectMapper.readTree(inputStream);

        return response;

    }

    public static JsonNode addressRequest(String query, String restKey, String url, Integer size, Integer page) throws IOException {
        
        String encodedAddress = EncodeDataUtil.getCoordinate(query);
        String key = "KakaoAK " + restKey;

        URL searchUrl = new URL(url + encodedAddress + "&size=" + size + "&page=" + page);

        HttpURLConnection connect = (HttpURLConnection)searchUrl.openConnection();
        connect.setRequestMethod("GET");
        connect.setRequestProperty("Authorization", key);
        connect.setRequestProperty("content-type", "application/json");

        InputStream inputStream = connect.getInputStream();
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode response = objectMapper.readTree(inputStream);

        return response;

    }

}
