package com.example.antoan.planit.utils;

import java.sql.Time;
import java.sql.Timestamp;

/**
 * Created by Antoan on 2/19/2017.
 */

public class InputValidator {
    public static boolean IsInValidLenghtRange(String str, Integer min, Integer max){
        if (str.isEmpty() || str.length() < min || str.length() > max) {
            return false;
        } else {
            return true;
        }
    }

    public static boolean IsValidEmail(String email) {
        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return false;
        } else {
            return true;
        }
    }

    public static boolean isValidTime(String time){
        if(time.isEmpty()){
            return false;
        }

        if(!time.matches("\\d{1,2}:\\d{1,2}")){
            return false;
        }

        boolean isValid = true;
        String[] times = time.split(":");
        Integer hour  = Integer.valueOf(times[0]);
        if(0>hour || hour >=24){
            isValid = false;
        }
        Integer minutes = Integer.valueOf(times[1]);
        if(0>minutes || minutes >=60){
            isValid = false;
        }


        return isValid;
    }

    public static boolean checkIfEndIsBiggerThanStar(String start,String end){
        String[] starts = start.split(":");
        String[] ends = end.split(":");

        if(!start.matches("\\d{1,2}:\\d{1,2}")){
            return false;
        }

        if(!end.matches("\\d{1,2}:\\d{1,2}")){
            return false;
        }

        Integer startHour = Integer.valueOf(starts[0]);
        Integer startMinute = Integer.valueOf(starts[1]);

        Integer endHour = Integer.valueOf(ends[0]);
        Integer endMinute = Integer.valueOf(ends[1]);

        if(startHour > endHour){
            return false;
        }
        if((startHour == endHour)&&(startMinute>endMinute)){
            return  false;
        }

        return true;
    }
}
