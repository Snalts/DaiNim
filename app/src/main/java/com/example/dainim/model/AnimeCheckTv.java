package com.example.dainim.model;

import org.json.JSONException;
import org.json.JSONObject;

public class AnimeCheckTv {

    public Boolean checkTv(JSONObject data) throws JSONException {
        return data.get("type").equals("TV");
    }
}
