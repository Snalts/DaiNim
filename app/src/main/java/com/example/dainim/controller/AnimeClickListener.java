package com.example.dainim.controller;

import android.content.Intent;
import android.view.View;

import com.example.dainim.model.Anime;
import com.example.dainim.view.MainActivity;

public class AnimeClickListener implements View.OnClickListener
{
    private Intent intent;
    private MainActivity activity;
    private Anime anime;

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
