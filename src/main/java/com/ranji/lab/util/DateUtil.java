package com.ranji.lab.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    public static Date StringToDate(String time, String format){
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Date formatTime = null;
        try {
            formatTime = sdf.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return formatTime;
    }

    public static String DateToString(Date date,String format){
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        String formatTime = sdf.format(date);
        return formatTime;
    }


}
