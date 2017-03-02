package com.data.models;

import java.io.Serializable;

/**
 * Created by hristov on 3.3.2017 г..
 */

public class SimpleDate implements Serializable {
    private int year;
    private int month;
    private int day;

    public SimpleDate() {
    }

    public SimpleDate(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }
}
