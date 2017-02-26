package com.example.antoan.planit.utils;

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
}
