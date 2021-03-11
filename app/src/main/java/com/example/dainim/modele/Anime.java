package com.example.dainim.modele;

import android.util.Log;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import org.json.JSONException;
import org.json.JSONObject;

public class Anime{
    JSONObject data;
    int id_anim;
    Object obj;
    public Anime(int id,Object obj) throws InterruptedException {
        this.id_anim = id;
        this.obj = obj;
        String s_url = "https://api.jikan.moe/v3/anime/" + id_anim;
        UrlConnect link = new UrlConnect(s_url,obj);
        link.start();
        link.join();
        data = link.getData();
    }
    public Anime(JSONObject data){
        this.data = data;
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