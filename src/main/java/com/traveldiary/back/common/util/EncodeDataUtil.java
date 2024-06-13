package com.traveldiary.back.common.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class EncodeDataUtil {
    
    public static String getCoordinate(String query) throws UnsupportedEncodingException  {

        String encodedAddress = URLEncoder.encode(query, "UTF-8");
        return encodedAddress;

    }

}
