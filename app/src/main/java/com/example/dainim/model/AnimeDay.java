package com.example.dainim.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class AnimeDay {

    private ArrayList<Anime> anime_day_list;
    private JSONObject data;
    private EnumWeek enumday;

    public AnimeDay(EnumWeek enumDay, Object obj) throws InterruptedException, JSONException {
        anime_day_list = new ArrayList<Anime>();
        this.enumday = enumDay;
        String s_url = "https://api.jikan.moe/v3/schedule/" + enumday.getMinimum();
        UrlConnect link = new UrlConnect(s_url,obj);
        link.start();
        link.join();
        data = link.getData();
        JSONArray arr_data = data.getJSONArray(enumday.getMinimum());
        AnimeCheckTv check = new AnimeCheckTv();
        for(int i=0;i < arr_data.length()-1;i++){
            if(check.checkTv(arr_data.getJSONObject(i))) {
                anime_day_list.add(new Anime(arr_data.getJSONObject(i)));
            }
        }
    }

    /*
     * ArrayList<Anime> return the list of anime.
     * @throws getting back JSONException if data not declare
     */
    public ArrayList<Anime> getAnimeList(){
        return anime_day_list;
    }

    /*
     * getAnime return the Japanese style.
     * @throws getting back JSONException if data not declare
     */
    public Anime getAnime(int index) throws JSONException {
        return anime_day_list.get(index);
    }
    /*
     * Method toString
     * Return Anim class to String
     */
    public String toString() {
        return "Anime list " + anime_day_list.toString();
    }
}
