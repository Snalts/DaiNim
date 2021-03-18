package com.example.dainim.model;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * AnimeCheckTV class, check if Anime has a TV size type, (not manga or ligth novel type)
 */
public class AnimeCheckTv {
    /**
     * checkTv, check if Anime is a Tv size
     * @param data JSONObject, Contains value data
     * @return Boolean True if TV size, False else
     * @throws JSONException If the data don't have the type
     */
    public Boolean checkTv(JSONObject data) throws JSONException {
        return data.get("type").equals("TV");
    }
}
