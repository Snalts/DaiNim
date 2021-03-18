package com.example.dainim.model;


import java.io.Serializable;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Class Anime, this class define one anime values, Implements Serializable and Comparable
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
     * Time String in anime
     */
    private String time;

    /**
     * Constructor for firebase
     */
    public Anime(){}

    /**
     * Constructor for Anime
     * @param id, need for my anime Id
     * @param obj need for Synchronise UrlConnect
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
     * Constructor for setData ( telling with AnimeSeason or AnimeDay)
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
    * @param data -> data for anime
    *@throws JSONException getting back if data not declare
    */
    private void setValue(JSONObject data) throws JSONException {
        this.title = data.getString("title");
        this.synopsis = data.getString("synopsis");
        this.url_image = data.getString("image_url");
        this.time = data.getString("airing_start");
        this.time = this.time.split("T")[1];

    }


    /**
     * getTitle return the title.
     * @return title
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * getSynopsis the synopsis for Anime
     * @return synopsis
     */
    public String getSynopsis(){
        return this.synopsis;
    }

    /**
     * getImage return the url image for Anime
     * @return url_image
     */
    public String getImage(){
        return this.url_image;
    }

    /**
     * getTime return the time of publication for Anime
     * @return time
     */
    public String getTime(){
        return this.time.substring(0, this.time.indexOf('+'));
    }
    
    /*
    *Method toString
    *@return Anim class to String
    */
    public String toString(){
        return "Titre : " + this.title + "| Url_Image" + this.url_image + " Synopsis" + this.synopsis;
    }

    @Override
    public int compareTo(Anime a) {
        return this.time.compareTo(a.time);
    }
}