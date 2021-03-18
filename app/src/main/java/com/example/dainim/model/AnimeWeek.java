package com.example.dainim.model;

import org.json.JSONException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * AnimeWeek class, get all anime for the week, implement Singleton pattern
 */
public class AnimeWeek {
    /**
     *
     */
    private HashMap<EnumWeek,AnimeDay> dictionary_week;
    /**
     * getCurrent day in the week
     */
    private EnumWeek current;
    /**
     * LastDay add in AnimeWeek
     */
    private EnumWeek lastAdd;
    /**
     * Singleton pattern, instance one time
     */
    private static AnimeWeek animeWeek;
    /**
     * Need for UrlConnect
     */
    public Object obj;

    /**
     * Constructor
     * @param obj Object
     * @throws InterruptedException if interrupted
     * @throws JSONException if data not found
     */
    private AnimeWeek(Object obj) throws InterruptedException, JSONException {
        this.obj = obj;
        dictionary_week = new HashMap<>();
        current = EnumWeek.getCurrent();
        dictionary_week.put(current,new AnimeDay(current,obj));
        lastAdd = current;
    }

    /**
     * Singleton Pattern, need just one instance
     * @param obj Object
     * @return AnimeWeek instance
     */
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

    /**
     * getAnimeDictionnary, return all day instance in the dictionary
     * @return HashMap<EnumWeek,AnimeDay></EnumWeek,AnimeDay>
     */
    public HashMap<EnumWeek,AnimeDay> getAnimeDictionary(){
        return this.dictionary_week;
    }

    /**
     * getCurrentDay, return animeList for currentDay
     * @return ArrayList<Anime>
     */
    public ArrayList<Anime> getCurrentDay(){
        return this.dictionary_week.get(animeWeek.current).getAnimeList();
    }

    /**
     * getWeek, get all anime for one day
     * @param day EnumWeek
     * @return ArrayList<Anime></Anime>
     */
    public ArrayList<Anime> getWeek(EnumWeek day){
        return this.dictionary_week.get(day).getAnimeList();
    }

    /**
     * Instance next day in the dictionary and return result
     * @return ArrayList<Anime></Anime>
     */
    public ArrayList<Anime> getNextDay(){
        this.lastAdd = EnumWeek.nextDay(this.lastAdd);
        if(!this.dictionary_week.containsKey(this.lastAdd)){
            this.setNewWeek(this.lastAdd);
        }
        return this.getWeek(this.lastAdd);
    }
    /**
     * Instance previous day in the dictionary and return result
     * @return ArrayList<Anime></Anime>
     */
    public ArrayList<Anime> getPreviousDay(){
        this.lastAdd = EnumWeek.previousDay(this.lastAdd);
        if(!this.dictionary_week.containsKey(this.lastAdd)){
            this.setNewWeek(this.lastAdd);
        }
        return this.getWeek(this.lastAdd);
    }

    /**
     * set new day in the dictionary
     * @param newDay EnumWeek
     */
    private void setNewWeek(EnumWeek newDay) {
        try {
            this.dictionary_week.put(newDay,new AnimeDay(newDay,this.obj));
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * get all anime in one Day
     * @param day EnumWeek
     * @return AnimeDay
     */
    public AnimeDay getAnimeDay(EnumWeek day) {
        return this.dictionary_week.get(day);
    }

    /*
     * Method toString
     * Return AnimeWeek class to String
     */
    public String toString() {
        return "Anime list " + this.dictionary_week.toString();
    }
}
