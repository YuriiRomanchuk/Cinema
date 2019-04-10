package com.cinema.model.converter.utility;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimeConverter {

    public static Date convertStringDate(String date) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat();
        format.applyPattern("yyyy-mm-dd hh:mm:ss");
        return format.parse(date);
    }

    public static Date receiveBeginOfDay(Date date) {
        return receiveMomentOfDay(date, 0, 0, 0, 0);
    }

    public static Date receiveEndOfDay(Date date) {
        return receiveMomentOfDay(date, 23, 59, 59, 999);
    }


    private static Date receiveMomentOfDay(Date date, int hour, int minute, int second, int millisecond) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, hour);
        cal.set(Calendar.MINUTE, minute);
        cal.set(Calendar.SECOND, second);
        cal.set(Calendar.MILLISECOND, millisecond);

        return cal.getTime();
    }


}