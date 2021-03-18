package com.example.dainim.model;


import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.SimpleTimeZone;

import org.json.JSONException;
import org.json.JSONObject;

public class Anime implements Serializable,Comparable<Anime> {
    private static final long serialVersionUID = 1350092881346723535L;
    private int id_anim;
    private String title;
    private String url_image;
    private String synopsis;
    private String time;


    public Anime(){}

    public Anime(int id,Object obj) throws InterruptedException {
        this.id_anim = id;
        String s_url = "https://api.jikan.moe/v3/anime/" + id_anim;
        UrlConnect link = new UrlConnect(s_url,obj);
        link.start();
        link.join();
        JSONObject data = link.getData();
        try {
            setValue(data);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public Anime(JSONObject data){
        try {
            setValue(data);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    /*
    * setValue, get data to json API Jikan.
    * @param JSONObject data -> data for anime
    *@throws getting back JSONException if data not declare
     */
    private void setValue(JSONObject data) throws JSONException {
        this.title = data.getString("title");
        this.synopsis = data.getString("synopsis");
        this.url_image = data.getString("image_url");
        this.time = data.getString("airing_start");
        this.time = this.time.split("T")[1];

    }
    /*
     * getTitle return the title.
     */
    public String getTitle() {
        return this.title;
    }
    /*
    * getSynopsis return the synopsis for Anime
    */
    public String getSynopsis(){
        return this.synopsis;
    }

    public String getImage(){
        return this.url_image;
    }

    public String getTime(){
        return this.time;
    }
    
    /*
      * Method toString
      * Return Anim class to String
     */
    public String toString(){
        return "Titre : " + this.title + "| Url_Image" + this.url_image + " Synopsis" + this.synopsis;
    }

    @Override
    public int compareTo(Anime a) {
        return this.time.compareTo(a.time);
    }
}