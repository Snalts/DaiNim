package com.example.dainim.model;


import java.io.Serializable;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Anime class, this class defines one anime values, Implements Serializable and Comparable
 */
public class Anime implements Serializable,Comparable<Anime> {
    /**
     * serialVersionUID for Serializable
     */
    private static final long serialVersionUID = 1350092881346723535L;

    /**
     * id_anim int for data in API
     */
    private int id_anim;

    /**
     * Title String for Anime Title
     */
    private String title;

    /**
     * url_image String for picture Anime
     */
    private String url_image;

    /**
     * Synopsis String in Anime
     */
    private String synopsis;

    /**
     * Time String in Anime
     */
    private String time;

    /**
     * Constructor for firebase
     */
    public Anime(){}

    /**
     * Constructor for Anime
     * @param id, needed to get anime Id
     * @param obj needed to synchronise UrlConnect
     * @throws InterruptedException if the system is Interrupted
     */
    public Anime(int id,Object obj) throws InterruptedException {
        this.id_anim = id;
        String s_url = "https://api.jikan.moe/v3/anime/" + id_anim;
        UrlConnect link = new UrlConnect(s_url,obj);
        link.start();
        link.join();
        JSONObject data = link.getData();
        try {
            setValue(data);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * Constructor for setData (telling with AnimeSeason or AnimeDay)
     * @param data JSONObject data for one Anime
     */
    public Anime(JSONObject data){
        try {
            setValue(data);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    /**
     * setValue, set data in Anime class with data json API Jikan.
     * @param JSONObject data -> data for anime
     * @throws getting back JSONException if data not declare
     */
    private void setValue(JSONObject data) throws JSONException {
        this.title = data.getString("title");
        this.synopsis = data.getString("synopsis");
        this.url_image = data.getString("image_url");
        this.time = data.getString("airing_start");
        this.time = this.time.split("T")[1];

    }


    /**
     * getTitle return the anime title.
     * @return title
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * getSynopsis return the anime synopsis
     * @return synopsis
     */
    public String getSynopsis(){
        return this.synopsis;
    }

    /**
     * getImage return the anime image url
     * @return url_image
     */
    public String getImage(){
        return this.url_image;
    }

    /**
     * getTime return the anime publication time
     * @return time
     */
    public String getTime(){
        return this.time.substring(0, this.time.indexOf('+'));
    }
    
    /**
     *Method toString
     *@return Anim class to String
     */
    public String toString(){
        return "Titre : " + this.title + "| Url_Image" + this.url_image + " Synopsis" + this.synopsis;
    }

    /**
     * Method that compare two anime together depending on their airing time
     * @param a the anime which will be compared to the calling one
     * @return an integer positive if the calling anime is greater than a, negative if the calling anime is less than a or 0 if they are equal.
     */
    @Override
    public int compareTo(Anime a) {
        return this.time.compareTo(a.time);
    }
}