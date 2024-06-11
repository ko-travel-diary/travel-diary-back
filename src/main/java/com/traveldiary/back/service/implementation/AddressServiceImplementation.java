package com.traveldiary.back.service.implementation;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.traveldiary.back.service.AddressService;

@Service
public class AddressServiceImplementation implements AddressService{

    @Value("${kakao.rest-api-key}") private String restKey;

    @Override
    public JsonNode  SearchAddress(String query) throws UnsupportedEncodingException{

    String url = "http://dapi.kakao.com/v2/local/search/address.json?query=";
    String key = "KakaoAK " + restKey;
    String encodeAddress = URLEncoder.encode(query, "UTF-8");

    URL searchUrl;

        try {

            // 인코딩 O
            // System.out.println(endcodeAddress);

            // "http://dapi.kakao.com/v2/local/search/address.json?query=%EB%B6%80%EC%82%B0%EC%A7%84%EA%B5%AC"
            searchUrl = new URL(url + encodeAddress);
            // System.out.println(searcUrl);

            // HTTPURLConnection : HTTP 요청을 수행
            HttpURLConnection con = (HttpURLConnection)searchUrl.openConnection();

            // GET 메서드 사용
            con.setRequestMethod("GET");
            // 헤더에 추가 정보 포함
            con.setRequestProperty("Authorization",key);
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
