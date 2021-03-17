package com.example.dainim.view;

import android.widget.TextView;

import com.example.dainim.model.Anime;
import com.example.dainim.model.AnimeSeason;
import com.example.dainim.model.AnimeWeek;
import com.example.dainim.model.EnumSeason;

import org.json.JSONException;

public class AnimeView {
    private Anime a;
    private TextView text;
    public AnimeView(int id, TextView text, Object obj) throws InterruptedException, JSONException {
        this.text = text;
        //this.a = new Anime(id, obj);
        AnimeSeason a = AnimeSeason.getInstance(obj);
        System.out.println(a.getAnime(a.getYear(),a.getEnumSeason(),0));
        a.newSeason(2021,EnumSeason.SPRING);

        /*AnimeSeason at = new AnimeSeason(2018,Season.WINTER,obj);
        /*a.start();
        a.join();
        at.start();
        at.join();*/
        //System.out.println("TEST:" + a.toString());
        //this.text.setText(a.getTitle());
    }

    public Anime getAnime()
    {
        return this.a;
    }
}
