package com.example.dainim.model;

public enum EnumWeek {
    MONDAY("monday"),TUESDAY("tuesday"),WEDNESAY("wednesday"),THURSDAY("thursday"),FRIDAY("friday"),SATURDAY("saturday"),SUNDAY("sunday");
    private String minimum;

    private EnumWeek(String minimum){
        this.minimum = minimum;
    }

    public String getMinimum() {
        return minimum;
    }
}
