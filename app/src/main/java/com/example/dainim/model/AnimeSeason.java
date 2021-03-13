package com.example.dainim.model;

import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class AnimeSeason extends Thread {
    private ArrayList<Anime> anime_list;
    private JSONObject data;
    private int year;
    private EnumSeason enumSeason;
    private Object obj;

    public AnimeSeason(int year, EnumSeason enumSeason, Object obj) throws InterruptedException, JSONException {

        anime_list = new ArrayList<Anime>();
        this.year = year;
        this.enumSeason = enumSeason;
        this.obj = obj;
        String s_url = "https://api.jikan.moe/v3/season/"+year + "/"+ enumSeason.getMinimum();
        UrlConnect link = new UrlConnect(s_url,obj);
        link.start();
        link.join();
        data = link.getData();
        JSONArray arr_data = data.getJSONArray("anime");
        AnimeCheckTv check = new AnimeCheckTv();
        for(int i=0;i < arr_data.length()-1;i++){
        if(check.checkTv(arr_data.getJSONObject(i))) {
                anime_list.add(new Anime(arr_data.getJSONObject(i)));
            }
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