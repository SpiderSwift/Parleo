package com.leathersoft.parleo.util;

import android.os.Build;
import android.widget.DatePicker;
import android.widget.TimePicker;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtil {
    private DateUtil() {
    }

    //should work until +-7019 AC
    public static int calculateAge(Date birthDate, Date currentDate) {
        // validate inputs ...
        if(birthDate == null || currentDate == null){
            return 0;
        }

        DateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        int d1 = Integer.parseInt(formatter.format(birthDate));
        int d2 = Integer.parseInt(formatter.format(currentDate));
        int age = (d2 - d1) / 10000;
        return age;
    }

    public static java.util.Date getDateFromDatePicker(DatePicker datePicker){
        int day = datePicker.getDayOfMonth();
        int month = datePicker.getMonth();
        int year =  datePicker.getYear();

        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);

        return calendar.getTime();
    }


    public static java.util.Date getTimeFromTimePicker(TimePicker timePicker){

        int hour;
        int minute;
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.M){
            hour = timePicker.getCurrentHour();
            minute = timePicker.getCurrentMinute();
        } else{
            hour = timePicker.getHour();
            minute = timePicker.getMinute();
        }


        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY,hour);
        cal.set(Calendar.MINUTE,minute);

        Date d = cal.getTime();

        return d;
    }


    public static Date getDateFromStringDateAndStringTime(
            String date,
            String time,
            SimpleDateFormat dateFormat,
            SimpleDateFormat timeFormat)
    throws ParseException {

        Date resultDate = dateFormat.parse(date);
        Date resultTime = timeFormat.parse(time);

        Calendar dateCalendar = Calendar.getInstance();
        dateCalendar.setTime(resultDate);

        Calendar timeCalendar = Calendar.getInstance();
        timeCalendar.setTime(resultTime);


        Calendar resCalendar = Calendar.getInstance();

        resCalendar.set(Calendar.DAY_OF_MONTH, dateCalendar.get(Calendar.DAY_OF_MONTH));
        resCalendar.set(Calendar.MONTH, dateCalendar.get(Calendar.MONTH));
        resCalendar.set(Calendar.YEAR, dateCalendar.get(Calendar.YEAR));

        resCalendar.set(Calendar.HOUR, timeCalendar.get(Calendar.HOUR));
        resCalendar.set(Calendar.MINUTE, timeCalendar.get(Calendar.MINUTE));
        resCalendar.set(Calendar.SECOND, timeCalendar.get(Calendar.SECOND));

        return resCalendar.getTime();
    }

}
