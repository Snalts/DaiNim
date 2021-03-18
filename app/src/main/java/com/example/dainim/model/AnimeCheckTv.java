package com.example.dainim.model;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * AnimeCheckTV, verify if Anime is a type TV size, not manga or ligth novel
 */
public class AnimeCheckTv {
    /**
     * checkTv, verify if Anime is a Tv size
     * @param data JSONObject, Contains value data
     * @return Boolean True if TV size, False other
     * @throws JSONException If the data didn't have the type
     */
    public Boolean checkTv(JSONObject data) throws JSONException {
        return data.get("type").equals("TV");
    }
}
