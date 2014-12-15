package com.leapyear.felipe.leapyear;

import java.io.Serializable;

/**
 * Created by Felipe on 09/12/2014.
 */
public class Year implements Serializable {

    private int year;
    private int leapYear;

    public static final int INDETERMINATE = 0;
    public static final int LEAPYEAR = 1;
    public static final int NOTLEAPYEAR = 2;

    public Year(int year){
        this.year = year;
        this.leapYear = INDETERMINATE;
    }

    public Year(int year, int leapYear){
        this.year = year;
        this.leapYear = leapYear;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getLeapYear() {
        return leapYear;
    }

    public void setLeapYear(int leapYear) {
        this.leapYear = leapYear;
    }

    @Override
    public String toString() {
        return "Year: "+year+" LeapYear: "+String.valueOf(leapYear);
    }
}
