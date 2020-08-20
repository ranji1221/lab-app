package com.ranji.lab.util;

import java.sql.Time;
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
    public static Time strToTime(String strDate,String g) {
        String str = strDate;
        SimpleDateFormat format = new SimpleDateFormat(g);
        Date d = null;
        try {
            d = format.parse(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Time time = new java.sql.Time(d.getTime());
        return time.valueOf(str);
    }

    public static String DateToString(Date date,String format){
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        String formatTime = sdf.format(date);
        return formatTime;
    }


}
