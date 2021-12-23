package com.belajar.movies.belajarspring.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    public static final String FORMAT_DEFAULT = "yyyy-MM-dd HH:mm";
    public static final String FORMAT_DATEONE = "yyyyMMddHHmmss";

    public static Date convertStringToDate(String str){
        Date date = null;
        SimpleDateFormat df = new SimpleDateFormat(FORMAT_DEFAULT);
        try {
            date = df.parse(str);
        }catch (ParseException es){
            es.printStackTrace();
        }
        return date;
    }

    public static String dateFormat(String pattern) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat.format(new Date());
    }
}
