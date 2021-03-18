package com.example.dainim.controller;

import android.content.Intent;
import android.view.View;
import com.example.dainim.model.Anime;
import com.example.dainim.view.MainActivity;

/**
 * AnimeClickListener class, define event in the ImageButton, implements View.OnClickListener
 */
public class AnimeClickListener implements View.OnClickListener
{
    /**
     * Switch windows
     */
    private Intent intent;
    /**
     * The default windows
     */
    private MainActivity activity;
    /**
     * Image Button Anime
     */
    private Anime anime;

    /**
     * Constructor
     * @param activity MainActivity
     * @param intent Intent
     * @param anime Anime
     */
    public AnimeClickListener(MainActivity activity, Intent intent, Anime anime)
    {
        this.activity = activity;
        this.intent = intent;
        this.anime = anime;
    }

    @Override
    public void onClick(View v)
    {
        intent.putExtra("anime", this.anime);
        this.activity.startActivity(intent);
    }
}
