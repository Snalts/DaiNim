package com.example.dainim.model;


import java.util.Calendar;
import java.lang.String;

public enum EnumWeek {
    MONDAY("monday"),TUESDAY("tuesday"),WEDNESDAY("wednesday"),THURSDAY("thursday"),FRIDAY("friday"),SATURDAY("saturday"),SUNDAY("sunday");
    private String minimum;

    private EnumWeek(String minimum){
        this.minimum = minimum;
    }

    public String getMinimum() {
        return minimum;
    }

    public static EnumWeek getCurrent(){
        Calendar c = Calendar.getInstance();
        int day = c.get(Calendar.DAY_OF_WEEK);
        switch(day){
            case Calendar.MONDAY:
                return EnumWeek.MONDAY;
            case Calendar.TUESDAY:
                return EnumWeek.TUESDAY;
            case Calendar.WEDNESDAY:
                return EnumWeek.WEDNESDAY;
            case Calendar.THURSDAY:
                return EnumWeek.THURSDAY;
            case Calendar.FRIDAY:
                return EnumWeek.FRIDAY;
            case Calendar.SATURDAY:
                return EnumWeek.SATURDAY;
            case Calendar.SUNDAY:
                return EnumWeek.SUNDAY;
            default: return EnumWeek.MONDAY;
        }
    }
    public static EnumWeek nextDay(EnumWeek e){
        switch(e){
            case MONDAY:
                return EnumWeek.TUESDAY;
            case TUESDAY:
                return EnumWeek.WEDNESDAY;
            case WEDNESDAY:
                return EnumWeek.THURSDAY;
            case THURSDAY:
                return EnumWeek.FRIDAY;
            case FRIDAY:
                return EnumWeek.SATURDAY;
            case SATURDAY:
                return EnumWeek.SUNDAY;
            case SUNDAY:
                return EnumWeek.MONDAY;
            default: return EnumWeek.MONDAY;
        }
    }
    public static EnumWeek lastDay(EnumWeek e){
        switch(e){
            case MONDAY:
                return EnumWeek.SUNDAY;
            case TUESDAY:
                return EnumWeek.MONDAY;
            case WEDNESDAY:
                return EnumWeek.TUESDAY;
            case THURSDAY:
                return EnumWeek.WEDNESDAY;
            case FRIDAY:
                return EnumWeek.THURSDAY;
            case SATURDAY:
                return EnumWeek.FRIDAY;
            case SUNDAY:
                return EnumWeek.SATURDAY;
            default: return EnumWeek.MONDAY;
        }
    }
}
