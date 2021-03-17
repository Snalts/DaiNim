package com.example.dainim.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class AnimeWeek {

    private HashMap<EnumWeek,AnimeDay> h_week_anime;
    private EnumWeek current;
    private EnumWeek lastAdd;
    private static AnimeWeek animeWeek;
    public Object obj;

    private AnimeWeek(Object obj) throws InterruptedException, JSONException {
        this.obj = obj;
        h_week_anime = new HashMap<EnumWeek,AnimeDay>();
        current = EnumWeek.getCurrent();
        h_week_anime.put(current,new AnimeDay(current,obj));
        lastAdd = current;
    }

    public static AnimeWeek getInstance(Object obj){
        if (animeWeek == null) {
            try {
                animeWeek = new AnimeWeek(obj);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return animeWeek;
    }
    /*
     * ArrayList<Anime> return the list of anime.
     * @throws getting back JSONException if data not declare
     */
    public HashMap<EnumWeek,AnimeDay> getAnimeList(){
        return animeWeek.h_week_anime;
    }

    public ArrayList<Anime> getCurrentDay(){
        return animeWeek.h_week_anime.get(animeWeek.current).getAnimeList();
    }

    public ArrayList<Anime> getWeek(EnumWeek e){
        return animeWeek.h_week_anime.get(e).getAnimeList();
    }

    public ArrayList<Anime> nextDay(){
        animeWeek.lastAdd = EnumWeek.nextDay(animeWeek.current);
        if(!animeWeek.h_week_anime.containsKey(animeWeek.lastAdd)){
            animeWeek.setNewWeek(animeWeek.lastAdd);
        }
        return animeWeek.getWeek(animeWeek.lastAdd);
    }

    public ArrayList<Anime> previousDay(){
        animeWeek.lastAdd = EnumWeek.lastDay(animeWeek.current);
        if(!animeWeek.h_week_anime.containsKey(animeWeek.lastAdd)){
            animeWeek.setNewWeek(animeWeek.lastAdd);
        }
        return animeWeek.getWeek(animeWeek.lastAdd);
    }

    private void setNewWeek(EnumWeek newDay) {
        try {
            animeWeek.h_week_anime.put(newDay,new AnimeDay(newDay,this.obj));
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /*
     * getAnime return the Japanese style.
     * @throws getting back JSONException if data not declare
     */
    public AnimeDay getAnime(EnumWeek e) throws JSONException {
        return animeWeek.h_week_anime.get(e);
    }
    /*
     * Method toString
     * Return Anim class to String
     */
    public String toString() {
        return "Anime list " + animeWeek.h_week_anime.toString();
    }
}
