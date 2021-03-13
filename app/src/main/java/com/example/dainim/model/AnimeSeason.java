package com.example.dainim.model;

import android.util.Log;

import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class AnimeSeason extends Thread {
    private ArrayList<Anime> anime_list;
    private JSONObject data;
    private int year;
    private Season season;
    private Object obj;

    public AnimeSeason(int year, Season season, Object obj) throws InterruptedException, JSONException {
        anime_list = new ArrayList<Anime>();
        this.year = year;
        this.season = season;
        this.obj = obj;
        String s_url = "https://api.jikan.moe/v3/season/"+year + "/"+ season.getMinimum();
        UrlConnect link = new UrlConnect(s_url,obj);
        link.start();
        link.join();
        data = link.getData();
        JSONArray arr_data = data.getJSONArray("anime");
        for(int i=0;i < arr_data.length()-1;i++){
            anime_list.add(new Anime(arr_data.getJSONObject(i)));
        }
    }

    /*
     * ArrayList<Anime> return the list of anime.
     * @throws getting back JSONException if data not declare
     */
    public ArrayList<Anime> getAnimeList(){
        return anime_list;
    }

    /*
     * getAnime return the Japanese style.
     * @throws getting back JSONException if data not declare
     */
    public Anime getAnime(int index) throws JSONException {
        return anime_list.get(index);
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
    public String toString() {
       return "Anime list " + anime_list.toString();
    }
}