package com.example.dainim.model;


import android.os.Build;

import androidx.annotation.RequiresApi;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public enum EnumSeason {
    SUMMER("summer"),SPRING("spring"),FALL("fall"),WINTER("winter");
    private String minimum;

    private EnumSeason(String minimum){
        this.minimum = minimum;
    }

    public String getMinimum() {
        return minimum;
    }

    public static EnumSeason getThisSeason(){
        EnumSeason retour;
        Date date = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int day = c.getTime().getDate();
        int month = Calendar.MONTH;
        if( day >= 20 && month == 11 || day < 20 && month == 2 || month < 2){
            retour = EnumSeason.WINTER;
        }
        else if(day >= 20 && month == 2 || day < 21 && month == 5 || month > 2 && month < 5)
            retour = EnumSeason.SPRING;
        else if(day >= 21 && month == 5 || day < 22 && month == 8 || month > 5 && month < 8){
            retour = EnumSeason.SUMMER;
        }
        else {
            retour = EnumSeason.FALL;
        }
        return retour;
    }
}
