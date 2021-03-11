package com.example.dainim.model;

import android.util.Log;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class AnimeSeason extends Thread {
    JSONObject data;
    int year;
    Season season;
    Object obj;
    public AnimeSeason(int year, Season season, Object obj) throws InterruptedException, JSONException {
        this.year = year;
        this.season = season;
        this.obj = obj;
        String s_url = "https://api.jikan.moe/v3/season/"+year + "/"+ season.getMinimum();
        UrlConnect link = new UrlConnect(s_url,obj);
        link.start();
        link.join();
        data = link.getData();
        /*for(JSONArray arr, (data.getJSONArray("anime"))){

        }*/

    }
    /*
     * getTitle return the Japaness title in romanji.
     * @throws getting back JSONException if data not declare
     */
    public String getTitle() throws JSONException {
        return data.getString("title");
    }

    /*
     * getTitleJapanese return the Japanese style.
     * @throws getting back JSONException if data not declare
     */
    public String getTitleJapanese() throws JSONException {
        return data.getString("title_japanese");
    }

    /*
     * getSynopsis return the synopsis for Anime
     * @throws getting back JSONException if data not declare
     */
    public String getSynopsis() throws JSONException {
        return data.getString("synopsis");
    }

    /*
     * Method toString
     * Return Anim class to String
     */
    public String toString(){
        String back = "Bonjour";
        try {
            back = "Test" + this.getTitle() + ':' + this.getTitleJapanese();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return back;
    }
}