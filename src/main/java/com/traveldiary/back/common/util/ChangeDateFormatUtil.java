package com.traveldiary.back.common.util;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;


public class ChangeDateFormatUtil {
    
    public static String nowDate() throws Exception {

        Date now = Date.from(Instant.now());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy-MM-dd");
        String dateTime = simpleDateFormat.format(now);

        return dateTime;
    }

}