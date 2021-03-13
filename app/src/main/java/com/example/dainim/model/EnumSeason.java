package com.example.dainim.model;


public enum EnumSeason {
    SUMMER("summer"),SPRING("spring"),FALL("fall"),WINTER("winter");
    private String minimum;

    private EnumSeason(String minimum){
        this.minimum = minimum;
    }

    public String getMinimum() {
        return minimum;
    }
}
