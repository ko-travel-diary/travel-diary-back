package com.traveldiary.back.service;

import java.io.UnsupportedEncodingException;

import com.fasterxml.jackson.databind.JsonNode;

public interface AddressService {

    JsonNode SearchAddress (String query) throws UnsupportedEncodingException;

}
