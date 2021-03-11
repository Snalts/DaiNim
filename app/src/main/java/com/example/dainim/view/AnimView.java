package com.example.dainim.view;

import android.widget.TextView;

import com.example.dainim.model.AnimeSeason;
import com.example.dainim.model.Season;
import com.example.dainim.model.Anime;

import org.json.JSONException;

public class AnimView {
    private Anime a;
    private TextView text;
    public AnimView(int id,TextView text,Object obj) throws InterruptedException{
        this.text = text;
        Anime a = new Anime(id,obj);
    /*    AnimeSeason at = new AnimeSeason(2018,Season.WINTER,obj);
        /*a.start();
        a.join();
        at.start();
        at.join();*/
        System.out.println("TEST:" + a.toString());
        //this.text.setText(a.getTitle());
    }

}
