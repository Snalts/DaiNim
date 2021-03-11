package com.example.dainim.modele;

import android.util.Log;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class AnimeSeason extends Thread {
    JSONObject data;
    int year;
    Season season;
    Object obj;
    public AnimeSeason(int year, Season season, Object obj) {
        this.year = year;
        this.season = season;
        this.obj = obj;
    }

    @Override
    public void run() {
        try {
            synchronized (obj) {
                URL url = new URL("https://api.jikan.moe/v3/season/"+year + "/"+ season.getMinimum());
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                conn.connect();

                //Getting the response code
                int responsecode = conn.getResponseCode();
                if (responsecode != 200) {
                    throw new RuntimeException("HttpResponseCode: " + responsecode);
                } else {

                    String inline = "";
                    Scanner scanner = new Scanner(url.openStream());

                    //Write all the JSON data into a string using a scanner
                    while (scanner.hasNext()) {
                        inline += scanner.nextLine();
                    }

                    //Close the scanner
                    scanner.close();

                    data = new JSONObject(inline);
                    JSONArray Anim = data.getJSONArray("anime");
                    System.out.println((Anim.getJSONObject(1)).get("title"));

                    //System.out.println(getTitle());
                }
            }
        }
        catch (Exception e) {
            Log.d("Exception","Exception" + e.toString());
        }

    }


    /*
     * getTitle return the Japaness title in romanji.
     * @throws getting back JSONException if data not declare
     */
    public String getTitle() throws JSONException {
        return data.getString("title");
    }

    /*
     * getTitleJapanese return the Japanese style.
     * @throws getting back JSONException if data not declare
     */
    public String getTitleJapanese() throws JSONException {
        return data.getString("title_japanese");
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
    public String toString(){
        String back = "Bonjour";
        try {
            back = "Test" + this.getTitle() + ':' + this.getTitleJapanese();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return back;
    }
}