package com.example.dainim.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class AnimeSeason extends Thread {

    private HashMap<Integer, HashMap<EnumSeason,ArrayList<Anime>>> dictionnary;
    private JSONObject data;
    private int year;
    private EnumSeason enumSeason;
    private Object obj;
    private static AnimeSeason animeSeason;

    private AnimeSeason(Object obj) throws JSONException{
        this.dictionnary = new HashMap<Integer, HashMap<EnumSeason,ArrayList<Anime>>>();
        this.year = Calendar.getInstance().get(Calendar.YEAR);
        this.obj = obj;
        this.enumSeason = EnumSeason.getThisSeason();
        this.setDictionnary(this.setValue());
    }

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
    public void newSeason(int year, EnumSeason enumSeason) throws JSONException {
        if((!this.dictionnary.containsValue(year)) && (!this.dictionnary.get(year).containsValue(enumSeason))){
            this.year = year;
            this.enumSeason = enumSeason;
            this.setDictionnary(this.setValue());
        }
    }
    private ArrayList<Anime> setValue() throws JSONException {
        ArrayList<Anime> anime_list = new ArrayList<Anime>();
        String s_url = "https://api.jikan.moe/v3/season/"+this.year + "/"+ enumSeason.getMinimum();
        UrlConnect link = new UrlConnect(s_url,obj);
        try {
            link.start();
            link.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        data = link.getData();
        JSONArray arr_data = data.getJSONArray("anime");
        AnimeCheckTv check = new AnimeCheckTv();
        for(int i=0;i < arr_data.length()-1;i++){
            if(check.checkTv(arr_data.getJSONObject(i))) {
                anime_list.add(new Anime(arr_data.getJSONObject(i)));
            }
        }
        return anime_list;
    }
    private void setDictionnary(ArrayList<Anime> arrayList){
        HashMap<EnumSeason,ArrayList<Anime>> dic_season;
        if(dictionnary.containsKey(year)){
            dic_season = dictionnary.get(year);
        }
        else{
            dic_season = new HashMap<EnumSeason,ArrayList<Anime>>();
        }
        if(!dic_season.containsValue(this.enumSeason)){
            dic_season.put(this.enumSeason,arrayList);
            this.dictionnary.put(this.year,dic_season);
        }
    }

    /*
     * ArrayList<Anime> return the list of anime.
     * @throws getting back JSONException if data not declare
     */
    public ArrayList<Anime> getAnimeList(int year,EnumSeason e){
        return dictionnary.get(year).get(e);
    }

    public int getYear() {
        return year;
    }

    public EnumSeason getEnumSeason() {
        return enumSeason;
    }

    /*
     * getAnime return the Japanese style.
     * @throws getting back JSONException if data not declare
     */
    public Anime getAnime(int year,EnumSeason e,int index) throws JSONException {
        return dictionnary.get(year).get(e).get(index);
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
       return "Anime Season get " + dictionnary.toString();
    }
}