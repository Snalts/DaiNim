package com.example.dainim.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.dainim.R;
import com.example.dainim.model.Anime;
import com.squareup.picasso.Picasso;

public class PlanningActivity extends BaseActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        this.configureAll();
    }
}
