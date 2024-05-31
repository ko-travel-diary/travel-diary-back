package com.traveldiary.back.service;

import java.io.UnsupportedEncodingException;

import com.fasterxml.jackson.databind.JsonNode;

public interface LocationService {
    
    JsonNode SearchLocation (String query) throws UnsupportedEncodingException;

}
