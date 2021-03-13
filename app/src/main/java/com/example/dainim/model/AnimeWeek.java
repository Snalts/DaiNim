package com.example.dainim.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class AnimeWeek {

    private HashMap<EnumWeek,AnimeDay> h_week_anime;

    public AnimeWeek(Object obj) throws InterruptedException, JSONException {
        h_week_anime = new HashMap<EnumWeek,AnimeDay>();
        for(EnumWeek e : EnumWeek.values()){
            h_week_anime.put(e,new AnimeDay(e,obj));
            System.out.println(h_week_anime.get(e).getAnime(0).getTitle());
        }
    }

    /*
     * ArrayList<Anime> return the list of anime.
     * @throws getting back JSONException if data not declare
     */
    public HashMap<EnumWeek,AnimeDay> getAnimeList(){
        return h_week_anime;
    }

    /*
     * getAnime return the Japanese style.
     * @throws getting back JSONException if data not declare
     */
    public AnimeDay getAnime(EnumWeek e) throws JSONException {
        return h_week_anime.get(e);
    }
    /*
     * Method toString
     * Return Anim class to String
     */
    public String toString() {
        return "Anime list " + h_week_anime.toString();
    }
}
