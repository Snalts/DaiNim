package com.example.dainim.model;


public enum Season {
    SUMMER("summer"),SPRING("spring"),FALL("fall"),WINTER("winter");
    private String minimum;

    private Season(String minimum){
        this.minimum = minimum;
    }

    public String getMinimum() {
        return minimum;
    }
}
