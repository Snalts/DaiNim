package com.example.dainim.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * AnimeSeason, Implements Singleton Pattern, get all anime for One season (Winter 2021)
 */
public final class AnimeSeason{
    /**
     * dictionnary HashMap, contains all data after request.
     */
    private HashMap<Integer, HashMap<EnumSeason,ArrayList<Anime>>> dictionary;

    /**
     * Contains last year instance in the dictionary
     */
    private int year;
    /**
     * Contains last enumSeason in the dicctionary
     */
    private EnumSeason enumSeason;
    /**
     * Need for UrlConnect
     */
    private Object obj;
    /**
     * Singleton Pattern, didn't need to recall API
     */
    private static AnimeSeason animeSeason;

    /**
     * Constructor for AnimeSeason
     * @param obj Need for UrlConnect
     * @throws JSONException getting back JSONException if data not declare
     */
    private AnimeSeason(Object obj) throws JSONException{
        this.dictionary = new HashMap<>();
        this.year = Calendar.getInstance().get(Calendar.YEAR);
        this.obj = obj;
        this.enumSeason = EnumSeason.getThisSeason();
        this.setDictionary(this.setValue());
    }

    /**
     * getInstance, Singleton Pattern, need one instance for AnimeSeason
     * @param obj Need to UrlConnect
     * @return AnimeSeason
     */
    public static AnimeSeason getInstance(Object obj){
        System.out.println(animeSeason);
        if(animeSeason == null){
            try {
                animeSeason = new AnimeSeason(obj);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        System.out.println(animeSeason);
        return animeSeason;
    }

    /**
     * newSeason, add newSeason in the dictionary
     * @param year new year season
     * @param enumSeason new enum season
     */
    public void newSeason(int year, EnumSeason enumSeason) {
        if((!this.dictionary.containsValue(year)) && (!this.dictionary.get(year).containsValue(enumSeason)))
        {
        this.year = year;
        this.enumSeason = enumSeason;
            try {
                this.setDictionary(this.setValue());
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * setValue in ArrayList<Anime>
     * @return new ArrayList for the season in Anime
     * @throws JSONException getting back JSONException if data not found
     */
    private ArrayList<Anime> setValue() throws JSONException {
        ArrayList<Anime> anime_list = new ArrayList<>();
        String s_url = "https://api.jikan.moe/v3/season/"+ this.year + "/"+ this.enumSeason.getMinimum();
        UrlConnect link = new UrlConnect(s_url,obj);
        try {
            link.start();
            link.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        JSONObject data = link.getData();
        JSONArray arr_data = data.getJSONArray("anime");
        AnimeCheckTv check = new AnimeCheckTv();
        for(int i=0;i < arr_data.length()-1;i++){
            if(check.checkTv(arr_data.getJSONObject(i))) {
                anime_list.add(new Anime(arr_data.getJSONObject(i)));
            }
        }
        return anime_list;
    }

    /**
     * Add new arrayList in the dictionary
     * @param arrayList New season data
     */
    private void setDictionary(ArrayList<Anime> arrayList){
        HashMap<EnumSeason,ArrayList<Anime>> dic_season;
        if(this.dictionary.containsKey(year)){
            dic_season = this.dictionary.get(year);
        }
        else{
            dic_season = new HashMap<>();
        }
        if(!dic_season.containsValue(this.enumSeason)){
            dic_season.put(this.enumSeason,arrayList);
            this.dictionary.put(this.year,dic_season);
        }
    }

    /*
     * ArrayList<Anime> return the list of anime.
     */
    public ArrayList<Anime> getAnimeList(int year,EnumSeason e){
        return this.dictionary.get(year).get(e);
    }

    /**
     * get last year add
     * @return year
     */
    public int getYear() {
        return this.year;
    }

    /**
     * get last season add
     * @return enumSeason
     */
    public EnumSeason getEnumSeason() {
        return this.enumSeason;
    }

    /*
     *
     * @throws getting back JSONException if data not declare
     */

    /**
     * getAnime return the anime in the dictionnary.
     * @param year int
     * @param season EnumSeason
     * @param index Index in the arraylist Anime
     * @return Anime
     */
    public Anime getAnime(int year,EnumSeason season,int index) {
        return this.dictionary.get(year).get(season).get(index);
    }

    /*
     * Method toString
     * Return AnimeSeason class to String
     */
    public String toString() {
       return "Anime Season get " + this.dictionary.toString();
    }
}