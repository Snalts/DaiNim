package com.example.dainim.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * AnimeDay class, get anime data for one day
 */
public class AnimeDay {
    /**
     * ArrayList<Anime> Contains all Anime going out in one day
     */
    private ArrayList<Anime> anime_day_list;

    /**
     * EnumWeek, Contains all days in an Enumeration
     */
    private EnumWeek enumday;

    /**
     * Constructor for class AnimeDay
     * @param enumDay Enumeration for day
     * @param obj Needed for UrlConnect to Synchronize the thread
     * @throws InterruptedException If the application is interrupted
     * @throws JSONException If the JSONObject data is not found
     */
    public AnimeDay(EnumWeek enumDay, Object obj) throws InterruptedException, JSONException {
        anime_day_list = new ArrayList<>();
        this.enumday = enumDay;
        String s_url = "https://api.jikan.moe/v3/schedule/" + enumday.getMinimum();
        UrlConnect link = new UrlConnect(s_url,obj);
        link.start();
        link.join();
        JSONObject data = link.getData();
        JSONArray arr_data = data.getJSONArray(enumday.getMinimum());
        AnimeCheckTv check = new AnimeCheckTv();
        for(int i=0;i < arr_data.length()-1;i++){
            if(check.checkTv(arr_data.getJSONObject(i))) {
                anime_day_list.add(new Anime(arr_data.getJSONObject(i)));
            }
        }
    }


    /**
     * returns the list of anime for one Day
     * @return ArrayList<Anime>
     */
    public ArrayList<Anime> getAnimeList(){
        return anime_day_list;
    }

    /**
     * Return an anime in the anime day list
     * @param index The position in the anime list
     * @return The anime at the index position in the list
     */
    public Anime getAnime(int index){
        return anime_day_list.get(index);
    }

    /**
     * toString
     * @return Anime class to String
     */
    public String toString() {
        return "Anime list " + anime_day_list.toString();
    }
}
