package com.traveldiary.back.service;

import java.io.UnsupportedEncodingException;

import com.fasterxml.jackson.databind.JsonNode;

public interface AddressService {

    JsonNode SearchCoordinate (String query) throws UnsupportedEncodingException;
    JsonNode SearchAddress (String query, Integer page, Integer size) throws UnsupportedEncodingException;

}
